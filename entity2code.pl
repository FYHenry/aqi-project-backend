#!/usr/bin/perl
use strict;
use warnings;
use constant ROOT => 'src/main/java/fr/diginamic/aqiprojectbackend';
use constant TARGETDIR => 'essai.pl.d';

if(! defined($ARGV[0])) {
  die("Undefined argument!\n");
}

if(! $ARGV =~ m/^([A-Z][a-z0-9]*)+$/){
  die("Bad Java class name!\n");
}

my $arg = $ARGV[0];
if("$arg" eq '-h' || "$arg" eq '--help'){
  print("Usage : ${0} (-h|--help)
        ${0} ENTITY_NAME

    -h --help      Texte d’aide.
    ENTITY_NAME    Nom de la classe entité à traiter.
                   Exemple: UserAccount.
");
  exit(0);
}

my $subpkg;
my $in;
foreach my $spkg ('account', 'map', 'forum') {
  if( -f ROOT."/entity/${spkg}/${arg}.java"){
    open($in, "<".ROOT."/entity/${spkg}/${arg}.java")
      or die("open: $!");
    $subpkg = $spkg;
    last;
  }
}

if(! defined($in)) {
  print("Entity not found. :-(\n");
  exit(1);
} else {
  print("Entity found! :-)\n");
  print("Within the subpackage ${subpkg}.\n");
}

print("\n");
my %std;
my %one;
my %many;
my ($idname, $idtype);
my @params;
my @paramtypes;
my $isid = 0;
foreach (<$in>) {
  if((! $isid) && (! defined($idname)) && $_ =~ m/\@Id/){
    $isid = 1;
  }
  if($_ =~ m/^\s*private\s+(int|double|float|String|LocalDate(Time)?)\s+([a-z][0-9a-zA-Z]*)(?=\s*;)/){
#    print("\{${1} ${3}\}");
    if($isid && (! defined($idname))){
      $idtype = "$1";
      $idname = "$3";
    } else {
      $std{$3} = "${1}";
      push(@paramtypes, "$1");
      push(@params, "$3");
    }
  }
  elsif($_ =~ m/^\s*private\s+List\<([A-Z][0-9a-zA-Z]*)\>\s+([a-z][0-9a-zA-Z]*)(?=\s*;)/){
#    print("\{List<${1}> ${2}\}");
    $many{$2} = "${1}";
    push(@paramtypes, "$1");
    push(@params, "$2");
  }
  elsif($_ =~ m/^\s*private\s+([A-Z][0-9a-zA-Z]*)\s+([a-z][0-9a-zA-Z]*)(?=\s*;)/){
#    print("\{${1} ${2}\}");
    $one{$2} = "${1}";
    push(@paramtypes, "$1");
    push(@params, "$2");
  }
#  print("${_}");
}
close($in);

print("\nIdentifier :\n");
print("${idtype} ${idname}\n");
print("\nStandards :\n");
foreach (keys(%std)){
  print("${std{$_}} ${_}\n");
}
print("\nOnes :\n");
foreach (keys(%one)){
  print("${one{$_}} ${_}\n");
}
print("\nManies :\n");
foreach (keys(%many)){
  print("${many{$_}} ${_}\n");
}

print("\n");
my %formats = ('Class' => $arg,
  'Instance' => $arg,
  'Uppercase-comment' => $arg,
  'Lowercase-comment' => $arg,
  'URL' => $arg);
$formats{'Instance'} =~ s/^([A-Z])/\l$1/;
$formats{'Uppercase-comment'} =~ s/^([A-Z][a-z0-9]*)([A-Z])/$1 \l$2/g;
$formats{'Lowercase-comment'} =~ s/^([A-Z][a-z0-9]*)([A-Z])/\l$1 \l$2/g;
$formats{'URL'} =~ s/^([A-Z][a-z0-9]*)([A-Z])/\l$1-\l$2/g;
foreach (keys(%formats)){
  print("${_} format : ${formats{$_}}\n")
}

# To test and insert parameters in DTO class.
# Parameters :
#   Array index
sub testandinsert{
  my $i = $_[0];
  my $result;
  if(defined($many{"${params[$i]}"})){
    $result = "List<Integer> ${params[$i]}Ids";
  } elsif(defined($one{"${params[$i]}"})){
    $result = "int ${params[$i]}Id";
  } else {
    $result = "${paramtypes[$i]} ${params[$i]}";
  }
  return $result;
}

# To test and insert comments in DTO class.
# Parameters :
#   Array index
sub testandcomment{
  my $i = $_[0];
  my $result;
  my $ucparam = $params[$i];
  $ucparam =~ s/([A-Z])/ \l$1/g;
  $ucparam =~ s/^([a-z])/\u$1/g;
  if(defined($many{"${params[$i]}"})){
    $result = " * \@param ${params[$i]}Ids ${ucparam} idenfifiers\n";
  } elsif(defined($one{"${params[$i]}"})){
    $result = " * \@param ${params[$i]}Id ${ucparam} identifier\n";
  } else {
    $result = " * \@param ${params[$i]} ${ucparam}\n";
  }
  return $result;
}

# To write DTO class file.
sub writedtoclass{
  my $out;
  open($out, ">".TARGETDIR."/dto/${subpkg}/in/${arg}DtoIn.java")
    or die("open: $!");
  print($out "");
  close($out);
  open($out, ">>".TARGETDIR."/dto/${subpkg}/in/${arg}DtoIn.java")
    or die("open: $!");
  print($out "package fr.diginamic.aqiprojectbackend.dto.${subpkg}.in;\n\n");
  my $manysize = keys(%many);
  if($manysize != 0){
    print($out "import java.util.List;\n\n");
  }
  print($out "/**\n * ${formats{'Uppercase-comment'}} DTO input\n");
    foreach (0..$#params){
      print($out testandcomment($_));
    }
  print($out " */\npublic record ${formats{'Class'}}DtoIn(");
  my $paramsize = @params;
  if($paramsize == 0){
    print($out ") {}\n");
  }
  elsif($paramsize == 1){
    print($out "        ", testandinsert(0), ") {}\n");
  }
  else {
    print($out testandinsert(0), ",\n");
    foreach (1..($#params - 1)){
      print($out "        ", testandinsert($_), ",\n");
    }
    print($out "        ", testandinsert($#params), ") {}\n");
  }
  close($out);
}

# To write class file.
# Parameters :
#   package name (lowercase)
sub writeclass{
  my $out;
  my $lcname = $_[0];
  my $ucname = $_[0];
  if($ucname =~ m/^[a-z][a-z0-9]*$/){
    $ucname =~ s/^([a-z])/\u$1/;
  } else {
    die("Bad package name!");
  }
  open($in, "<".TARGETDIR."/${lcname}/CLASS${ucname}.java")
    or die("open: $!");
  open($out, ">".TARGETDIR."/${lcname}/${subpkg}/${arg}${ucname}.java")
    or die("open: $!");
  print($out "");
  close($out);
  open($out, ">>".TARGETDIR."/${lcname}/${subpkg}/${arg}${ucname}.java")
    or die("open: $!");
  foreach (<$in>) {
    $_ =~ s/SUBPKG/$subpkg/g;
    $_ =~ s/CLASS/$formats{'Class'}/g;
    $_ =~ s/INSTANCE/$formats{'Instance'}/g;
    $_ =~ s/UCCOMMENT/$formats{'Uppercase-comment'}/g;
    $_ =~ s/LCCOMMENT/$formats{'Lowercase-comment'}/g;
    $_ =~ s/URLNAME/$formats{'URL'}/g;
    print($out "$_");
  }
  close($in);
  close($out);
}

writeclass('repository');
writeclass('controller');
writedtoclass();

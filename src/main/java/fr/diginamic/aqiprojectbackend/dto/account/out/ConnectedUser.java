package fr.diginamic.aqiprojectbackend.dto.account.out;

public record ConnectedUser(int id,
                            String firstName,
                            String lastName,
                            String email,
                            String cityName,
                            double cityLat,
                            double cityLong,
                            String address1,
                            String address2
                            ) {
}

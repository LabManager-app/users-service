# Users Service

## Opis mikrostoritve
**Users Service** je ena izmed mikrostoritev v spletni aplikaciji **Lab Manager**.
Njena glavna naloga je upravljanje uporabnikov sistema ter njihovih podatkov. Poleg osnovnega upravljanja uporabnikov storitev skrbi tudi za logično razmejevanje dostopa do funkcionalnosti aplikacije glede na vlogo uporabnika (npr. administrator, raziskovalec, študent).

Storitev je implementirana kot **SpringBoot REST API**. API je implementiran v datoteki UsersController.java.

## Funkcionalnosti

**UsersService.java:**
- Ustvarjanje uporabnika
- Pridobivanje vseh uporabnikov
- Pridobivaje podatkov uporabnika glede na ID
- Brisanje uporabnika

## Swagger API dokumentacija
Za podroben opis končnih točk mikrostoritve (formati zahtev/odgovorov, vračanje napak), ob zagonu mikrostoritve obiščite: http://localhost:8081/swagger-ui/index.html

## Entitete
- **`User`**: upravljanje uporabnikov; polja: `id`, `name`, `email`, `password`, `role` in `position`. Reprezentira račune uporabnikov, njihove vloge in položaje.

- **`Codes`**: Pomožni model za preverjanje kod (npr. admin/invite kode); vsebuje nabor statičnih kod in metode za preverjanje ter preslikavo kode v vlogo.


## Navodila za namestitev
**Predpogoji:**
- Java 17
- Maven

**Namestitev**
```bash
# lokalna namestitev repozitorija
cd labManager
git clone https://github.com/LabManager-app/users-service.git
cd users-service
```
**Zagon**
```bash
mvn clean package
mvn spring-boot:run

# ali (v primeru uporabe Docker)
docker compose up --build users-service
```

Mikrostoritev bo dostopna na http://localhost:8081

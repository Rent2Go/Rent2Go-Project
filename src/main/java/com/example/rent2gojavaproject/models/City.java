package com.example.rent2gojavaproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum City {
    ADANA("Adana"),
    ADIYAMAN("Adiyaman"),
    AFYONKARAHISAR("Afyonkarahisar"),
    AGRI("Agri"),
    AKSARAY("Aksaray"),
    AMASYA("Amasya"),
    ANKARA("Ankara"),
    ANTALYA("Antalya"),
    ARDAHAN("Ardahan"),
    ARTVIN("Artvin"),
    AYDIN("Aydin"),
    BALIKESIR("Balikesir"),
    BARTIN("Bartin"),
    BATMAN("Batman"),
    BAYBURT("Bayburt"),
    BILECIK("Bilecik"),
    BINGOL("Bingol"),
    BITLIS("Bitlis"),
    BOLU("Bolu"),
    BURDUR("Burdur"),
    BURSA("Bursa"),
    CANAKKALE("Canakkale"),
    CANKIRI("Cankiri"),
    CORUM("Corum"),
    DENIZLI("Denizli"),
    DIYARBAKIR("Diyarbakir"),
    DUZCE("Duzce"),
    EDIRNE("Edirne"),
    ELAZIG("Elazig"),
    ERZINCAN("Erzincan"),
    ERZURUM("Erzurum"),
    ESKISEHIR("Eskisehir"),
    GAZIANTEP("Gaziantep"),
    GIRESUN("Giresun"),
    GUMUSHANE("Gumushane"),
    HAKKARI("Hakkari"),
    HATAY("Hatay"),
    IGDIR("Igdir"),
    ISPARTA("Isparta"),
    ISTANBUL("Istanbul"),
    IZMIR("Izmir"),
    KAHIRAMANMARAS("Kahramanmaras"),
    KARABUK("Karabuk"),
    KARAMAN("Karaman"),
    KARS("Kars"),
    KASTAMONU("Kastamonu"),
    KAYSERI("Kayseri"),
    KIRIKKALE("Kirikkale"),
    KIRKLARELI("Kirklareli"),
    KIRSEHIR("Kirsehir"),
    KOCAELI("Kocaeli"),
    KONYA("Konya"),
    KUTAHYA("Kutahya"),
    MALATYA("Malatya"),
    MANISA("Manisa"),
    MARDIN("Mardin"),
    MERSIN("Mersin"),
    MUGLA("Mugla"),
    MUS("Mus"),
    NEVSEHIR("Nevsehir"),
    NIGDE("Nigde"),
    ORDU("Ordu"),
    OSMANIYE("Osmaniye"),
    RIZE("Rize"),
    SAKARYA("Sakarya"),
    SAMSUN("Samsun"),
    SIIRT("Siirt"),
    SINOP("Sinop"),
    SIVAS("Sivas"),
    SANLIURFA("Sanliurfa"),
    SIRNAK("Sirnak"),
    TEKIRDAG("Tekirdag"),
    TOKAT("Tokat"),
    TRABZON("Trabzon"),
    TUNCELI("Tunceli"),
    USAK("Usak"),
    VAN("Van"),
    YALOVA("Yalova"),
    YOZGAT("Yozgat"),
    ZONGULDAK("Zonguldak");


    private final String cityName;

    City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
}

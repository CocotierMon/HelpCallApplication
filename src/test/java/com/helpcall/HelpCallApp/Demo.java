package com.helpcall.HelpCallApp;

import com.helpcall.HelpCallApp.domain.Institution;
import com.helpcall.HelpCallApp.domain.Need;
import com.helpcall.HelpCallApp.domain.Volunteer;
import com.helpcall.HelpCallApp.service.InstitutionDbService;
import com.helpcall.HelpCallApp.service.NeedDbService;
import com.helpcall.HelpCallApp.service.VolunteerDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class Demo {

    @Autowired
    InstitutionDbService institutionDbService;
    @Autowired
    NeedDbService needDbService;
    @Autowired
    VolunteerDbService volunteerDbService;

    @Test
    void createData() {

        Institution institution = new Institution("Schronisko dla psów", "schronisko@schronisko.pl", "schronisko",
                51.565973367806954, 19.514465332031254, "Schronisko dla bezdomnych psów i kotów", "true",
                new ArrayList<>());
        Institution institution1 = new Institution("Dom samotnej matki", "domdlamam@domdlamam.pl", "domdlamam",
                51.82813964710642, 19.30160522460938, "Oaza dla samotnych matek w kryzysie", "true", new ArrayList<>());
        Institution institution2 = new Institution("Rodzina zastępcza", "ddziecka@ddziecka.pl", "ddziecka",
                51.819651601869644, 19.52407836914063, "Specjalistyczna rodzina zastępcza", "false", new ArrayList<>());
        Institution institution3 = new Institution("Katarzyna", "kasia@kasia", "kasia",
                51.86716405856181, 19.40322875976563, "Jestem osobą niepełnosprawną, z ograniczeniami w zakresie " +
                "poruszania się", "false", new ArrayList<>());

        Volunteer volunteer = new Volunteer("Staszek", "staszek@staszek.pl", "fistaszek", "51.798424491278745",
                "19.390869140625004", "Kocham psy i koty, każdą wolną chwilę spędzałbym opiekując sie zwierzętami", new ArrayList<>());
        Volunteer volunteer1 = new Volunteer("Ania", "ania@ania.pl", "aniaania", "51.781435604431195", "19.34280395507813",
                "Umiem opiekować się osobami niepełnosprawnymi, mogę pomóc w opiece nad bliską Ci osobą", new ArrayList<>());
        Volunteer volunteer2 = new Volunteer("Kazik", "kazik@kazik.pl", "kaziu", "51.79078028408291", "19.432067871093754",
                "Mam dużo wolnego czasu i jestem złotą rączką", new ArrayList<>());

        Need need = new Need("Karma dla młodych psów", "Potrzebujemy 200kg karmy dla młodych psów, do 1 roku, sterylizowanych",
                "51.565973367806954", "19.514465332031254", LocalDate.now(), new Institution(), new ArrayList<>());
        Need need1 = new Need("Ubrania dla mam i dzieci", "Brakuje nam ubranek dla dzieciw rozmiarach od 74 do 104 oraz ubrań dla mam w rozmiarach 36 - 40",
                "51.82813964710642", "19.30160522460938", LocalDate.now(), new Institution(), new ArrayList<>());
        Need need2 = new Need("Nauka gry na gitarze", "Jedno z naszych podopiecznych marzy, żeby nauczyć się grać na gitrze. " +
                "Szukamy osoby, która chciałaby nauczyć go podstaw", "51.819651601869644", "19.52407836914063", LocalDate.now(), new Institution(),
                new ArrayList<>());
        Need need3 = new Need("Spacery", "Chciałabym mieć towarzysza spacerów", "51.86716405856181", "19.40322875976563", LocalDate.now(),
                new Institution(), new ArrayList<>());

        List<Need> needList = new ArrayList<>();
        needList.add(need);
        List<Need> needList1 = new ArrayList<>();
        needList1.add(need1);
        List<Need> needList2 = new ArrayList<>();
        needList2.add(need2);
        List<Need> needList3 = new ArrayList<>();
        needList3.add(need3);

        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(volunteer);
        List<Volunteer> volunteers1 = new ArrayList<>();
        volunteers1.add(volunteer1);
        List<Volunteer> volunteers2 = new ArrayList<>();
        volunteers2.add(volunteer2);

        institution.setNeeds(needList);
        institution1.setNeeds(needList1);
        institution2.setNeeds(needList2);
        institution3.setNeeds(needList3);

        need.setInstitution(institution);
        need.setVolunteers(volunteers);
        need1.setInstitution(institution1);
        need1.setVolunteers(volunteers1);
        need2.setInstitution(institution2);
        need2.setVolunteers(volunteers2);
        need3.setInstitution(institution3);
        need3.setVolunteers(volunteers2);

        volunteer.setNeeds(needList);
        volunteer1.setNeeds(needList1);
        volunteer2.setNeeds(needList2);
        volunteer2.setNeeds(needList3);

        needDbService.saveNeed(need);
        needDbService.saveNeed(need1);
        needDbService.saveNeed(need2);
        needDbService.saveNeed(need3);

        volunteerDbService.saveVolunteer(volunteer);
        volunteerDbService.saveVolunteer(volunteer1);
        volunteerDbService.saveVolunteer(volunteer2);

        institutionDbService.saveInstitution(institution);
        institutionDbService.saveInstitution(institution1);
        institutionDbService.saveInstitution(institution2);
        institutionDbService.saveInstitution(institution3);
    }
}

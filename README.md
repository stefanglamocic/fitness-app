# fitness-app
## Projektni zadatak iz Internet programiranja 2024
Napraviti jednostavnu aplikaciju za online fitnes, koja korisnicima pruža mogućnost
pretraživanja fitnes programa, vođenje dnevnika aktivnosti, kao i prijedlog vježbi. Sistem se
sastoji od nekoliko dijelova koji su opisani u nastavku:
### Fitness online aplikacija
Ovo je centralna aplikacija sistema koja se koristi za pružanje i traženje online fitnes
programa. Aplikacija omogućava korisnicima pregled i učestvovanje u različitim fitnes
programima. Svaki program ima naziv, opis, kategoriju (npr. kardio, snaga, fleksibilnost,
HIIT), osnovne zajedničke osobine (cijena, nivo težine, trajanje), lokaciju održavanja (npr.
online, teretana, park), slike, informacije o instruktoru i kontakt. Za svaku kategoriju dodaju
se specifični atributi (npr. za kardio - vrsta aktivnosti, za snagu - vrsta sprave ili tjelesna
težina). Sve moguće kategorije i osobine/atributi definišu se u administratorskoj aplikaciji.
Potrebno je omogućiti filtriranje i pretraživanje po ovim kriterijumima. Nije dozvoljeno da se
istovremeno prikazuju svi programi već je potrebno podržati neku vrstu straničenja (klasično
straničenje - pagination, virtual scrol). Programi su prikazani kao kartice, sa obaveznim
prikazom naziva, slike i cijene. Klikom na jednu karticu otvaraju se detalji na novoj stranici
gdje su prikazane sve informacije. Moguće je postavljati pitanja, a sva konverzacija za
određeni program se prikazuje svim korisnicima kao komentar.
Korisnici koji nemaju nalog mogu pretraživati programe, otvarati detalje, ali ne mogu
postavljati pitanja ili učestvovati. Nalog se kreira na formi za registraciju gdje se unose ime,
prezime, grad, korisničko ime, lozinka, avatar (opciono) i mail. Nakon popunjavanja forme,
na mail se šalje link za potvrdu registracije i aktivaciju naloga, koji vodi korisnika na endpoint
za registraciju. Opciono obezbijediti Captcha servis za potvrdu da se radi stvarnom
korisniku, a ne botu, kao npr. https://javalite.io/captcha. Kada je nalog uspješno kreiran i
aktiviran, korisnik pristupa osnovnoj stranici, na kojoj ima dostupne sve funkcionalnosti u
vidu menija. Ako je nalog kreiran i nije aktiviran, korisnik prilikom prijave na sistem dobija
formu za aktivaciju (unosi tačno korisničko ime i lozinku) i tada se ponovo generiše link i
šalje na mail. Prijavljeni korisnici mogu promijeniti svoje podatke (osim korisničkog imena)
na posebnoj stranici. Osim toga, mogu pregledati prethodna učestvovanja u programima,
kupovinu istih i slično.
Učestvovanje se obavlja tako što korisnici biraju način plaćanja (karticom, PayPal, lično na
lokaciji), a program se evidentira kao učestvovan. Nije potrebno detaljno obraditi način
plaćanja, već samo osnovnu upotrebu (npr. unos broja kartice). Pod učestvovanjem se
podrazumijeva odlazak na lokaciju, ako je fitnes program uživo, ili prikaz YouTube videa za
online fitnes programe.Svaki korisnik može kreirati novi fitnes program koji će biti dostupan u pretrazi drugim
korisnicima. Osim toga, korisnik može pregledati svoje programe (aktivne i završene) i
obrisati bilo koji svoj program.
Korisnici koji imaju nalog mogu poslati poruku savjetnicima za izbor programa sa forme koja
se nalazi negdje u aplikaciji. Prilikom kontaktiranja čuva se informacija o korisniku i sadržaj
poruke. Takođe, korisnici mogu komunicirati međusobno putem poruka.
Na početnoj stranici aplikacije, prikazuje se RSS feed sa najnovijim vijestima i informacijama
iz svijeta fitnesa konzumiranjem RSS feed-a https://feeds.feedburner.com/AceFitFacts. Osim
toga, aplikacija konzumira API (https://api-ninjas.com/api/exercises) kako bi dala dnevne
prijedloge 10 vježbi sa instrukcijama registorvanim korisnicima. Vježbe se prikazuju sa
nazivom, instrukcijama i nivoom.
Registrovani korisnik može da vodi svoj dnevnik aktivnosti, prati rezultate vježbanja i
napredak. Korisnik može unositi informacije o vrsti vježbi, trajanju, intenzitetu i rezultatima.
Aplikacija omogućava korisnicima grafički prikaz napretka, uključujući gubitak kilograma,
kroz određeni period. Korisnik može download-ovati svoj dnevnik aktivnosti kao PDF
dokument.
Korisniku se nudi opcija pretplate na određenu kategoriju. Pretplaćenim korisnicima jednom
dnevno se na mail šalju novi programi kreirani za tu kategoriju.
Aplikacija mora imati uniforman izgled svih stranica i mora biti responsivna. Za izradu koristiti
Angular i SpringBoot i bazu podataka po izboru. Dozvoljeno je koristiti gotove biblioteke kao
što je Bootstrap ili Material. Sve funkcionalnosti SpringBoot aplikacije dostupne su preko
RESTful servisa.
### Administratorska aplikacija
Za pristup administratorskoj aplikaciji potrebno je imati nalog koji se otvara direktno u bazi
podataka (ime, prezime, korisničko ime, lozinka). Na početnoj stranici nalazi se forma za
prijavu. Ako je prijava uspješna otvara se stranica na kojoj se nalazi meni sa opcijama:

- Kategorije: omogućava upravljanje podacima (CRUD) o kategorijama i specifičnim kategorijama fitnes programa.
- Korisnici: omogućava upravljanje podacima (CRUD) o korisnicima fitness online aplikacije.
- Statistika: omogućava pregled logova fitness backend aplikacije.

Implementacija administratorske aplikacije mora biti pomoću JSP M2. Dozvoljeno je
korištenje gotovih biblioteka za dizajn korisničkog interfejsa.

### Aplikacija za savjetovanje
Za pristup aplikaciji za savjetovanje potrebno je imati nalog koji mora biti otvoren od strane
administratora kroz administratorski dio aplikacije. Ovaj nalog nije isti kao nalog za
administratorsku ili fitness online aplikaciju. Ako je prijava uspješna otvara se stranica na
kojoj savjetnik može pregledati sve primljene poruke. U tabeli se prikazuju sve nepročitane
poruke, a otvaranjem jedne status se mijenja u pročitana. Na poruke se odgovara slanjem
mail-a. Pored teksta, potrebno je omogućiti savjetniku i slanje dokumenta sa dodatnim
opisima ili slikom. Savjetnik može pretražiti sve poruke po sadržaju.
Aplikaciju za savjetovanje implementirati upotrebom JSP-a.

Napomene:
- Projektni zadatak se radi pojedinačno.
- Prilikom predaje projektnog zadatka potrebno je predati kompletan izvorni kod, model
podataka, kao i model aplikacija. Poželjno je definisati testne podatke.
- Pri realizaciji projektnog zadatka zabranjeno je korištenje generatora koda, osim onih
koji su korišteni na laboratorijskim vježbama ili predavanjima.
- Interfejs prema korisniku treba da bude organizovan na jednoobrazan način tj., sve
stranice treba da imaju sličan izgled i intuitivne kontrole i prikaz.
- Svaki vid serverske validacije potrebno je da bude što efikasnije realizovan.
Obavezno napraviti klijentsku validaciju.
- Nije dozvoljeno koristiti stored procedure, funkcije i okidače (triggere). Dakle, baza
podataka ne smije imati bilo kakvu logiku osim definisanih tabela i ograničenja koja
važe među njima.
- Ovaj projektni zadatak vrijedi do objave novog projektnog zadataka.

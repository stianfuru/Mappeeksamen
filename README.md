# Mappeeksamen i AlgDat Høst 2020.


Oppgave 1:
Denne oppgaven var relativt grei. Siden vi kunne kopiere koden fra boka så var dette relativt fort gjort. Det eneste jeg endret på var at jeg satt q til forelder når p ble opprettet og hvis q var null så la jeg inn at p.forelder = 0. Dette funket greit. Ikke så mye mer å si om denne egentlig ettersom vi bare skulle gjøre sånn at den funket med foreldre peker.


Oppgave 2:
Også en relativt enkel oppgave. Først sjekket jeg at input-verdien ikke var 0. så instansierte jeg Noden p som starter på rot, cmp som skal bruker compare-funksjonen. og en teller som er det som skal bli returna til slutt. så brukte jeg en while-løkke for å sjekke om verdien finnes. Hvis p.verdi var større enn verdien så går den til venstre, siden der er tallene mindre. Hvis p.verdi var mindre eller lik så går den til høyre, og i tillegg så tar heg teller++ hvis den var lik. Da finner man ut hvor mange ganger denne verdien er i treet.


Oppgave 3:
førstePostorden:
Denne var også helt grei. Var litt usikker på om den p-noden man får inn var roten så lagde bare en while-løkke på starten som ordnet i det. Deretter vet vi at første node i postorden er den som ligger lengst til venstre. Deretter var det bare å gå nedover til venstre og høyre hvis venstre var null helt til det ikke var barn igjen.

nestePostorden:
Denne slet jeg litt mer med. Her er det flere ting som kan skje avhengig av hvordan treet ser ut. Det første tilfellet er greit nok. Det er når p.forelder == null. Dette betyr at det bare var en node i treet og den stopper der. Neste tilfelle er at p var høyre barn. Denne er også grei siden da skal alltid forelder være neste. Men det siste tilfelle var litt verre, altså at p er et venstrebarn. her skal p videre til høyrebarn og evnt høyrebarn sine barn først. Her slet jeg litt. Fant til slutt ut at jeg kunne ta en while løkke inni dette tilfelle for å gå ned til barna til høyrebarna hvis de ikke bvar null. Da ordnet det seg.


Oppgave 4:
postOrden:
Det jeg brukte mest tid på her var egentlig å finne ut av oppgave.utførOppgave-greiene, men bortsett fra det gikk denne fint. Bare lagde en while løkke som gikk til den var tom og la inn nestePostorden etterhvert som den gikk.

postordenRecursive:
Denne var litt verre for meg. Prøvde først å gjøre noe liknende som den forrige med å bruke funksjonene fra oppg 3. men dette gikk ikke. Så derfor gikk jeg til forelesningsvideoene for å se om jeg hadde glemt noe som var blitt gått gjennom. Der så jeg på videoen om dybde først traversering hvor basically svaret lå. Måtte bare modifisere noen småting for å få det til å fungere.


Oppgave 5:
serialize:
Her Var det største problemet hvordan jeg skulle traversere riktig gjennom treet. Kom til slutt frem til å bruke ArrayDeque på samme måte som det ble gjort i forelesnings videoene og i tillegg ha en ArrayList som legger til i mens køen går. Dette funket fint.

deserialize:
Det vanskeligste her var egentlig å finne ut hvor jeg skulle begynne. Skjønte ikke helt den Comparator parameteren i starten, så det endte med at jeg bare prøvde meg frem litt.
kom frem til at jeg bare lagde nodetre og tok en for-løkke med leggInn funksjonen for å lage nytt tre. Dette funker og går gjennom testene.


Oppgave 6:
fjern: 
Denne slet jeg litt med. Det vanskeligste var egentlig å finne ut av den koden vi kunne kopiere. Denne var veldig uoversiktelig for meg. Prøvde masse greier, men fikk NullPointerException på alt jeg gjorde. Ble litt frustrert og spurte om hjelp fra en klassekamerat. Da fant jeg ut at jeg basically hadde gjort riktig, men jeg måtte legge inn noen if-setninger for å ikke på NPE.

fjernAlle: Denne var grei. Kjørte bare antall-funksjonen jeg lagde i oppgave 2 og lagde en for løkke som brukte fjern-funksjonen som gikk det antallet jeg fikk i antall-funksjonen. Her tulla jeg litt med antall--, men fant fort ut av det.

nullstill:
Her brukte jeg samme prinsipp som i serialize oppgaven med å gå gjennom via ArrayDeque, bare at her fjernet jeg nodene i stedet.

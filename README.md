
# Krtkoland

## Zadanie

Krtkoland je sieť podzemných bunkrov, nadzemných veží a jedného skladu. Sieť môže obsahovať viacero bunkrov a veží, ale sklad je vždy práve jeden. Bunkre, veže a sklad sú poprepájané tunelmi. Ak sa chceš dostať napr. z veže do skladu, musíš prekonať niekoľko tunelov, bunkrov a veží, aby si sa dostal do cieľa. V Krtkolande žijú krtkovia. A práve krtko Lukáš má jednu dôležitú úlohu: zotrvať na stráži v konkrétnej veži čo najdlhšie, aby vedel upozorniť na prípadného nepriateľa. Problém je v tom, že Lukáš býva hladný a jedlo sa nachádza len v sklade. Treba preto naprogramovať riešenie, ktoré poradí Lukášovi cestu, ako sa vie čo najrýchlejšie dostať z konkrétnej veže do skladu. Aby to nebolo také jednoduché, po ceste ho čakajú nielen rôzne prekážky, ale dokonca každý tunel môže mať rôznu dĺžku. Čo vieme o sieti Krtkolandu?

__Tunel__ má tieto vlastnosti: dĺžka v metroch, typ povrchu a či tam funguje osvetlenie. Existujú tri povrchy: 
- Asfalt – vtedy 1 m tunela prejdeš za 0.5 sekundy
- Blato – vtedy 1 m tunela prejdeš za 1 sekundu
- Štrk – vtedy 1 m tunela prejdeš za 0.75 sekundy
Ak v tuneli nefunguje svetlo, každý 1 m krtkovi trvá o 20% času dlhšie.

Ak chce krtko vyjsť z bunkra/skladu do tunela alebo chce vojsť z tunela do bunkra/skladu, musí otvoriť __dvere__. Farby dverí určujú, ako dlho bude dvere otvárať:
- Červené – 5 sekúnd
- Modré – 3 sekundy
- Zelené – 2 sekundy

Ak je krtko vo veži a chce prejsť tunelom do iného bunkra, veže alebo skladu, musí prejsť __rebríkom__. Podľa toho, koľko má rebrík schodíkov, sa určí čas zlezenia. Každá veža má rozdielnu dĺžku rebríka, pričom krtko Lukáš vie za 1s prejsť 2 schodíky.

## Príklady

Takže napr. ak chceš prejsť z jedného bunkra do druhého bunkra, celkový čas na to potrebný je súčet nasledovných troch hodnôt:
- čas otvárania výstupných dverí z bunkra,
- čas prechodu cez tunel,
- čas otvorenia vstupných dverí do bunkra. 


Ďalej, keď chceš napríklad prejsť z bunkra do veže, celkový čas na to potrebný je súčet týchto troch hodnôt:
- čas otvorenia výstupných dverí,
- čas potrebný na prechod tunelom,
- čas prejdenia rebríkom.

## Popis riešenia

Naprogramuj službu __KrtkoService__, do ktorej injectneš zvonku objekt __Krtkoland__ (sieť tunelov, bunkrov, veží a skladu) a potom naprogramuj metódu __theBestTimeAndPath__, kde vstupný parameter bude jedna konkrétna veža (odporúčam ako objekt). Úlohou metódy je vrátiť najlepší čas a cestu ako sa krtko Lukáš vie dostať do skladu. Ak je takých ciest viac, tak vráť práve jednu. Služba by mala dať vedieť ak neexistuje žiadna cesta do skladu. 

Samozrejme služba môže mat viacero metód, to necháme na uchádzačovi. __Krtkoland__ je ideálne jeden objekt, ktorý popisuje sieť, ale pre interné účely si služba môže tento objekt transformovať na iné dátové štruktúry. Konkrétne hodnoty Krtkolandu (siete) môžeš inicializovať priamo v kóde (napr. cez Java Collections). Nestrácaj čas s nejakou DB alebo načítavaním údajov zo súboru (ale je to samozrejme na tebe). Na ukážku vytvor aspoň 3 rôzne Krtkolandy na to, aby si cez JUnit testy otestoval správnosť metódy __KrtkoService: theBestTimeAndPath__ a zároveň aby sme aj my vedeli ľahko otestovať niektoré iné prípady.
Ak budeš mat čas, môžeš rozšíriť službu o ďalšiu metódu __KrtkoService: theBestTimeAndPathList__, ktorá vráti viacero ciest s rovnakým najlepším časom.

### Technický pohlaď
- Riešenie naprogramuj v Jave, na verzii nezáleží.
- Prosím nepoužívaj žiadne knižnice z tretích strán, okrem JUnit.
- Preferujeme OOP riešenie, nie procedurálne. Ber do úvahy, že služba KrtkoService a dátový objekt Krtkoland sa môže neskôr rozširovať. Tak ako to zvyčajne býva, business môže rozširovať svoje požiadavky. Preto preferujeme riešenie, ktoré je čo najviac abstraktné na rozšírenie resp. robustné
- Určite budeme pozerať na efektivitu celkového výpočtu. Ber do úvahy, že Krtkoland môže mať v produkcii aj niekoľko miliónov tunelov.
- Na konci dňa nás bude zaujímať celkový clean kód tvojho riešenia. Podľa toho zistíme aj tvoju profesionalitu a skúsenosť.
- Nevyžadujeme žiadnu dokumentáciu. Tam kde to dáva zmysel, kľudne pridaj krátky komentár.

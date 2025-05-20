# Krtkoland

Implementácia podzemných bunkrov, nadzemných veží a skladov.

## Popis packages

Celý projekt sa nachádza v com.kbsystems.zadanie.matusfila.krtkoland, kde nájdeme hlavný vstupný bod aplikácie, triedu `KrtkolandApplication`, a všetky packages. Tu je ich popis.

### core
Obsahuje hlavnú funkcionalitu Krtkolandu: implementáciu grafov všeobecne, niektoré algoritmy nad grafmi a konkrétnu implementáciu Krtkolandu, ktorá je založená na grafoch.

#### core.graphs
Je to vstupný balík pre grafy, kde v podbalíkku `interfaces` je definovaná štruktúra tried:

`interface Graph`
Definícia grafu ako množiny bodov a hrán, plus základné funkcionality nad grafmi.

`interface Vertex`
Všeobecná definícia vrcholu - každý vrchol musí mať napr. svoje vlastné ID.

`interface Edge`
Definícia hrany grafu ako objektu obsahujúceho počiatočný a koncový bod/vrchol.
Každá hrana má aj váhu - v základnej implementácii v `AbstractEdge` sa váha nastavuje na hodnotu 1, čo sa dá vnímať ak neohodnotený graf.
Pri ohodnotených grafoch stačí v potomkoch predefinovať metódu `getWeight` a z hrany sa stane ohodnotená hrana a z grafu obsahujúceho takúto hranu sa stane ohodnotený graf.

#### core.krtkoland

Tento balík obsahuje implementáciu Krtkolandu. Celý krtkoland je vnímaný ako ohodnotený neorientovaný graf.
Na ohodnotenie majú vplyv faktory ako dĺžka tunelu, typ povrchu tunelu, osvetlenie tunelu, farba dverí bunkrov a skladu podobne.

### controllers, models, services

Všetky tieto balíky obsahujú triedy relevantné v zmysle štandardných názvových konvencií java services.

`KrtkoRestController.findBestPath`
Rest služba, ktorá berie na vstup začiatočný a koncový vrchol a vypočíta najkratšiu cestu medzi nimi, spolu s hodnotou tej cesty. Vrcholmi v Krtkolande môžu byť bunkre, veže a sklady.

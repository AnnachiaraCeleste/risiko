/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

import java.util.ArrayList;

/**
 *
 * @author Annachiara
 */
public class Territorio {
    private String nome;
    private ArrayList<Territorio> confini;
    private Giocatore proprietario;
    
    /*
| Territorio           | Confini                                      |
|-----------------------|----------------------------------------------|
| Afghanistan           | China, Medio Oriente, Urali, Ucraina          
| Africa del Nord       | Brasile, Europa Meridionale, Europa Occidentale, Egitto, Africa Orientale, Congo
| Africa del Sud        | Africa Orientale, Congo, Madagascar
| Africa Orientale      | Africa del Sud, Congo, Madagascar, Egitto, Africa del Nord
| Alaska                | Alberta, Kamchatka, Territori del Nord Ovest                           |
| Alberta               | Alaska, Ontario, Stati Uniti Occidentali, Territori del Nord Ovest
| America Centrale      | Stati Uniti Orientali, Venezuela, Stati Uniti Occidentali
| Argentina             | Brasile, Perù
| Australia Occidentale | Australia Orientale, Indonesia, Nuova Guinea
//questi in teoria sono corretti'//    
    
| Australia Orientale   | Australia Occidentale, Nuova Guinea          |
| Brasile               | Africa del Nord, America Centrale, Argentina |
| Cina                  | Afghanistan, Giappone, Kamchatka, Mongolia   |
| Cita                  | Giappone, Mongolia, Siberia                  |
| Congo                 | Africa del Sud, Africa Orientale             |
| Egitto                | Europa Meridionale, Medio Oriente            |
| Europa Meridionale    | Africa del Nord, Europa Occidentale, Egitto  |
| Europa Occidentale    | Europa Meridionale, Gran Bretagna, Scandinavia|
| Europa Settentrionale | Groenlandia, Islanda, Scandinavia             |
| Giappone              | Cina, Kamchatka                              |
| Gran Bretagna         | Europa Occidentale, Islanda, Scandinavia      |
| Groenlandia           | Europa Settentrionale, Quebec, Territori del Nord O.|
| India                 | Afghanistan, Medio Oriente, Siam             |
| Indonesia             | Nuova Guinea, Siam                            |
| Islanda               | Europa Settentrionale, Groenlandia            |
| Jacuzia               | Kamchatka, Mongolia, Siberia                 |
| Kamchatka             | Alaska, Cina, Giappone, Jacuzia, Mongolia    |
| Madagascar            | Africa Orientale                              |
| Medio Oriente         | Africa del Nord, Egitto, India               |
| Mongolia              | Cina, Cita, Jacuzia, Kamchatka               |
| Nuova Guinea          | Australia Orientale, Indonesia               |
| Ontario               | Alberta, Quebec, Stati Uniti Orientali        |
| Perù                  | Brasile, Stati Uniti Occidentali              |
| Quebec                | Groenlandia, Ontario, Stati Uniti Orientali  |
| Scandinavia           | Europa Occidentale, Europa Settentrionale     |
| Siam                  | India, Indonesia                             |
| Siberia               | Cita, Jacuzia, Mongolia, Urali               |
| Stati Uniti Occident. | Alberta, Alaska, America Centrale, Perù       |
| Stati Uniti Orient.   | America Centrale, Ontario, Quebec, Territori del Nord O.|
| Territori del Nord O.  | Groenlandia, Quebec, Stati Uniti Orientali   |
| Ucraina               | Europa Meridionale, Europa Settentrionale, Urali|
| Urali                 | Siberia, Ucraina, Afghanistan                 |
| Venezuela             | America Centrale, Brasile                     |

Questa tabella rappresenta i territori e i loro confini 
    */
}

package com.cyriltheandroid.travelcase.designsystem.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent

@Composable
fun CountryBadge(
    intent: CountryIntent,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(Color(0xFFEBEBEB), CircleShape),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = intent.value.flag,
                intent = TextIntent.Small,
            )
            Text(
                text = intent.value.value,
                intent = TextIntent.Small,
                color = Color(0xFF000000)
            )
        }
    }
}

@Preview
@Composable
fun CountryBadgePreview() {
    CountryBadge(
        intent = CountryIntent.Afghanistan
    )
}

enum class CountryIntent(val value: CountryIntentValue) {
    Afghanistan(value = CountryIntentValue.Afghanistan()),
    AlandIslands(value = CountryIntentValue.AlandIslands()),
    Albania(value = CountryIntentValue.Albania()),
    Algeria(value = CountryIntentValue.Algeria()),
    AmericanSamoa(value = CountryIntentValue.AmericanSamoa()),
    Andorra(value = CountryIntentValue.Andorra()),
    Angola(value = CountryIntentValue.Angola()),
    Anguilla(value = CountryIntentValue.Anguilla()),
    Antarctica(value = CountryIntentValue.Antarctica()),
    AntiguaAndBarbuda(value = CountryIntentValue.AntiguaAndBarbuda()),
    Argentina(value = CountryIntentValue.Argentina()),
    Armenia(value = CountryIntentValue.Armenia()),
    Aruba(value = CountryIntentValue.Aruba()),
    Australia(value = CountryIntentValue.Australia()),
    Austria(value = CountryIntentValue.Austria()),
    Azerbaijan(value = CountryIntentValue.Azerbaijan()),
    Bahamas(value = CountryIntentValue.Bahamas()),
    Bahrain(value = CountryIntentValue.Bahrain()),
    Bangladesh(value = CountryIntentValue.Bangladesh()),
    Barbados(value = CountryIntentValue.Barbados()),
    Belarus(value = CountryIntentValue.Belarus()),
    Belgium(value = CountryIntentValue.Belgium()),
    Belize(value = CountryIntentValue.Belize()),
    Benin(value = CountryIntentValue.Benin()),
    Bermuda(value = CountryIntentValue.Bermuda()),
    Bhutan(value = CountryIntentValue.Bhutan()),
    Bolivia(value = CountryIntentValue.Bolivia()),
    BonaireSaintEustacheAndSaba(value = CountryIntentValue.BonaireSaintEustacheAndSaba()),
    BosniaAndHerzegovina(value = CountryIntentValue.BosniaAndHerzegovina()),
    Botswana(value = CountryIntentValue.Botswana()),
    BouvetIsland(value = CountryIntentValue.BouvetIsland()),
    Brazil(value = CountryIntentValue.Brazil()),
    BritishIndianOceanTerritory(value = CountryIntentValue.BritishIndianOceanTerritory()),
    BritishVirginIslands(value = CountryIntentValue.BritishVirginIslands()),
    Bulgaria(value = CountryIntentValue.Bulgaria()),
    BurkinaFaso(value = CountryIntentValue.BurkinaFaso()),
    Burundi(value = CountryIntentValue.Burundi()),
    Cambodia(value = CountryIntentValue.Cambodia()),
    Cameroon(value = CountryIntentValue.Cameroon()),
    Canada(value = CountryIntentValue.Canada()),
    CapeVerde(value = CountryIntentValue.CapeVerde()),
    CaymanIslands(value = CountryIntentValue.CaymanIslands()),
    CentralAfricanRepublic(value = CountryIntentValue.CentralAfricanRepublic()),
    Chad(value = CountryIntentValue.Chad()),
    Chile(value = CountryIntentValue.Chile()),
    China(value = CountryIntentValue.China()),
    ChristmasIsland(value = CountryIntentValue.ChristmasIsland()),
    CocosIslands(value = CountryIntentValue.CocosIslands()),
    Comoros(value = CountryIntentValue.Comoros()),
    CongoBrazzaville(value = CountryIntentValue.CongoBrazzaville()),
    CongoKinshasa(value = CountryIntentValue.CongoKinshasa()),
    CookIslands(value = CountryIntentValue.CookIslands()),
    CostaRica(value = CountryIntentValue.CostaRica()),
    Curacao(value = CountryIntentValue.Curacao()),
    Cyprus(value = CountryIntentValue.Cyprus()),
    Czechia(value = CountryIntentValue.Czechia()),
    Denmark(value = CountryIntentValue.Denmark()),
    Djibouti(value = CountryIntentValue.Djibouti()),
    Dominica(value = CountryIntentValue.Dominica()),
    DominicanRepublic(value = CountryIntentValue.DominicanRepublic()),
    EastTimor(value = CountryIntentValue.EastTimor()),
    Ecuador(value = CountryIntentValue.Ecuador()),
    Egypt(value = CountryIntentValue.Egypt()),
    ElSalvador(value = CountryIntentValue.ElSalvador()),
    EquatorialGuinea(value = CountryIntentValue.EquatorialGuinea()),
    Eritrea(value = CountryIntentValue.Eritrea()),
    Estonia(value = CountryIntentValue.Estonia()),
    Eswatini(value = CountryIntentValue.Eswatini()),
    Ethiopia(value = CountryIntentValue.Ethiopia()),
    FalklandIslands(value = CountryIntentValue.FalklandIslands()),
    FaroeIslands(value = CountryIntentValue.FaroeIslands()),
    Fiji(value = CountryIntentValue.Fiji()),
    Finland(value = CountryIntentValue.Finland()),
    France(value = CountryIntentValue.France()),
    FrenchGuiana(value = CountryIntentValue.FrenchGuiana()),
    FrenchPolynesia(value = CountryIntentValue.FrenchPolynesia()),
    FrenchSouthernTerritories(value = CountryIntentValue.FrenchSouthernTerritories()),
    Gabon(value = CountryIntentValue.Gabon()),
    Gambia(value = CountryIntentValue.Gambia()),
    Georgia(value = CountryIntentValue.Georgia()),
    Germany(value = CountryIntentValue.Germany()),
    Ghana(value = CountryIntentValue.Ghana()),
    Gibraltar(value = CountryIntentValue.Gibraltar()),
    Greece(value = CountryIntentValue.Greece()),
    Greenland(value = CountryIntentValue.Greenland()),
    Grenada(value = CountryIntentValue.Grenada()),
    Guadeloupe(value = CountryIntentValue.Guadeloupe()),
    Guam(value = CountryIntentValue.Guam()),
    Guatemala(value = CountryIntentValue.Guatemala()),
    Guernsey(value = CountryIntentValue.Guernsey()),
    Guinea(value = CountryIntentValue.Guinea()),
    GuineaBissau(value = CountryIntentValue.GuineaBissau()),
    Guyana(value = CountryIntentValue.Guyana()),
    Haiti(value = CountryIntentValue.Haiti()),
    HeardAndMcDonaldIslands(value = CountryIntentValue.HeardAndMcDonaldIslands()),
    Honduras(value = CountryIntentValue.Honduras()),
    HongKongSAR(value = CountryIntentValue.HongKongSAR()),
    Hungary(value = CountryIntentValue.Hungary()),
    Iceland(value = CountryIntentValue.Iceland()),
    India(value = CountryIntentValue.India()),
    Indonesia(value = CountryIntentValue.Indonesia()),
    Iran(value = CountryIntentValue.Iran()),
    Iraq(value = CountryIntentValue.Iraq()),
    Ireland(value = CountryIntentValue.Ireland()),
    IsleOfMan(value = CountryIntentValue.IsleOfMan()),
    Israel(value = CountryIntentValue.Israel()),
    Italy(value = CountryIntentValue.Italy()),
    Jamaica(value = CountryIntentValue.Jamaica()),
    Japan(value = CountryIntentValue.Japan()),
    Jersey(value = CountryIntentValue.Jersey()),
    Jordan(value = CountryIntentValue.Jordan()),
    Kazakhstan(value = CountryIntentValue.Kazakhstan()),
    Kenya(value = CountryIntentValue.Kenya()),
    Kiribati(value = CountryIntentValue.Kiribati()),
    Kosovo(value = CountryIntentValue.Kosovo()),
    Kuwait(value = CountryIntentValue.Kuwait()),
    Kyrgyzstan(value = CountryIntentValue.Kyrgyzstan()),
    Laos(value = CountryIntentValue.Laos()),
    Latvia(value = CountryIntentValue.Latvia()),
    Lebanon(value = CountryIntentValue.Lebanon()),
    Lesotho(value = CountryIntentValue.Lesotho()),
    Liberia(value = CountryIntentValue.Liberia()),
    Libya(value = CountryIntentValue.Libya()),
    Liechtenstein(value = CountryIntentValue.Liechtenstein()),
    Lithuania(value = CountryIntentValue.Lithuania()),
    Luxembourg(value = CountryIntentValue.Luxembourg()),
    MacaoSAR(value = CountryIntentValue.MacaoSAR()),
    Macedonia(value = CountryIntentValue.Macedonia()),
    Madagascar(value = CountryIntentValue.Madagascar()),
    Malawi(value = CountryIntentValue.Malawi()),
    Malaysia(value = CountryIntentValue.Malaysia()),
    Maldives(value = CountryIntentValue.Maldives()),
    Mali(value = CountryIntentValue.Mali()),
    Malta(value = CountryIntentValue.Malta()),
    MarshallIslands(value = CountryIntentValue.MarshallIslands()),
    Martinique(value = CountryIntentValue.Martinique()),
    Mauritania(value = CountryIntentValue.Mauritania()),
    Mauritius(value = CountryIntentValue.Mauritius()),
    Mayotte(value = CountryIntentValue.Mayotte()),
    Mexico(value = CountryIntentValue.Mexico()),
    Micronesia(value = CountryIntentValue.Micronesia()),
    Moldova(value = CountryIntentValue.Moldova()),
    Monaco(value = CountryIntentValue.Monaco()),
    Mongolia(value = CountryIntentValue.Mongolia()),
    Montenegro(value = CountryIntentValue.Montenegro()),
    Montserrat(value = CountryIntentValue.Montserrat()),
    Morocco(value = CountryIntentValue.Morocco()),
    Mozambique(value = CountryIntentValue.Mozambique()),
    Myanmar(value = CountryIntentValue.Myanmar()),
    Namibia(value = CountryIntentValue.Namibia()),
    Nauru(value = CountryIntentValue.Nauru()),
    Nepal(value = CountryIntentValue.Nepal()),
    Netherlands(value = CountryIntentValue.Netherlands()),
    NetherlandsAntilles(value = CountryIntentValue.NetherlandsAntilles()),
    NewCaledonia(value = CountryIntentValue.NewCaledonia()),
    NewZealand(value = CountryIntentValue.NewZealand()),
    Nicaragua(value = CountryIntentValue.Nicaragua()),
    Niger(value = CountryIntentValue.Niger()),
    Nigeria(value = CountryIntentValue.Nigeria()),
    Niue(value = CountryIntentValue.Niue()),
    NorfolkIsland(value = CountryIntentValue.NorfolkIsland()),
    NorthKorea(value = CountryIntentValue.NorthKorea()),
    NorthernMarianaIslands(value = CountryIntentValue.NorthernMarianaIslands()),
    Norway(value = CountryIntentValue.Norway()),
    Oman(value = CountryIntentValue.Oman()),
    Pakistan(value = CountryIntentValue.Pakistan()),
    Palau(value = CountryIntentValue.Palau()),
    PalestinianTerritories(value = CountryIntentValue.PalestinianTerritories()),
    Panama(value = CountryIntentValue.Panama()),
    PapuaNewGuinea(value = CountryIntentValue.PapuaNewGuinea()),
    Paraguay(value = CountryIntentValue.Paraguay()),
    Peru(value = CountryIntentValue.Peru()),
    Philippines(value = CountryIntentValue.Philippines()),
    PitcairnIslands(value = CountryIntentValue.PitcairnIslands()),
    Poland(value = CountryIntentValue.Poland()),
    Portugal(value = CountryIntentValue.Portugal()),
    PuertoRico(value = CountryIntentValue.PuertoRico()),
    Qatar(value = CountryIntentValue.Qatar()),
    Reunion(value = CountryIntentValue.Reunion()),
    Romania(value = CountryIntentValue.Romania()),
    Russia(value = CountryIntentValue.Russia()),
    Rwanda(value = CountryIntentValue.Rwanda()),
    SaintBarthelemy(value = CountryIntentValue.SaintBarthelemy()),
    SaintHelena(value = CountryIntentValue.SaintHelena()),
    SaintKittsAndNevis(value = CountryIntentValue.SaintKittsAndNevis()),
    SaintLucia(value = CountryIntentValue.SaintLucia()),
    SaintPierreAndMiquelon(value = CountryIntentValue.SaintPierreAndMiquelon()),
    SaintVincentAndTheGrenadines(value = CountryIntentValue.SaintVincentAndTheGrenadines()),
    Samoa(value = CountryIntentValue.Samoa()),
    SanMarino(value = CountryIntentValue.SanMarino()),
    SaoTomeAndPrincipe(value = CountryIntentValue.SaoTomeAndPrincipe()),
    SaudiArabia(value = CountryIntentValue.SaudiArabia()),
    Senegal(value = CountryIntentValue.Senegal()),
    Serbia(value = CountryIntentValue.Serbia()),
    Seychelles(value = CountryIntentValue.Seychelles()),
    SierraLeone(value = CountryIntentValue.SierraLeone()),
    Singapore(value = CountryIntentValue.Singapore()),
    Slovakia(value = CountryIntentValue.Slovakia()),
    Slovenia(value = CountryIntentValue.Slovenia()),
    SolomonIslands(value = CountryIntentValue.SolomonIslands()),
    Somalia(value = CountryIntentValue.Somalia()),
    Somaliland(value = CountryIntentValue.Somaliland()),
    SouthAfrica(value = CountryIntentValue.SouthAfrica()),
    SouthKorea(value = CountryIntentValue.SouthKorea()),
    SouthSudan(value = CountryIntentValue.SouthSudan()),
    Spain(value = CountryIntentValue.Spain()),
    SriLanka(value = CountryIntentValue.SriLanka()),
    Sudan(value = CountryIntentValue.Sudan()),
    Suriname(value = CountryIntentValue.Suriname()),
    SvalbardAndJanMayen(value = CountryIntentValue.SvalbardAndJanMayen()),
    Swaziland(value = CountryIntentValue.Swaziland()),
    Sweden(value = CountryIntentValue.Sweden()),
    Switzerland(value = CountryIntentValue.Switzerland()),
    Syria(value = CountryIntentValue.Syria()),
    Taiwan(value = CountryIntentValue.Taiwan()),
    Tajikistan(value = CountryIntentValue.Tajikistan()),
    Tanzania(value = CountryIntentValue.Tanzania()),
    Thailand(value = CountryIntentValue.Thailand()),
    Togo(value = CountryIntentValue.Togo()),
    Tokelau(value = CountryIntentValue.Tokelau()),
    Tonga(value = CountryIntentValue.Tonga()),
    Transnistrie(value = CountryIntentValue.Transnistrie()),
    TrinidadAndTobago(value = CountryIntentValue.TrinidadAndTobago()),
    Tunisia(value = CountryIntentValue.Tunisia()),
    Turkey(value = CountryIntentValue.Turkey()),
    Turkmenistan(value = CountryIntentValue.Turkmenistan()),
    TurksAndCaicosIslands(value = CountryIntentValue.TurksAndCaicosIslands()),
    Tuvalu(value = CountryIntentValue.Tuvalu()),
    Uganda(value = CountryIntentValue.Uganda()),
    Ukraine(value = CountryIntentValue.Ukraine()),
    UnitedArabEmirates(value = CountryIntentValue.UnitedArabEmirates()),
    UnitedKingdom(value = CountryIntentValue.UnitedKingdom()),
    UnitedStates(value = CountryIntentValue.UnitedStates()),
    USOutlyingIslands(value = CountryIntentValue.USOutlyingIslands()),
    USVirginIslands(value = CountryIntentValue.USVirginIslands()),
    Uruguay(value = CountryIntentValue.Uruguay()),
    Uzbekistan(value = CountryIntentValue.Uzbekistan()),
    Vanuatu(value = CountryIntentValue.Vanuatu()),
    VaticanCityState(value = CountryIntentValue.VaticanCityState()),
    Venezuela(value = CountryIntentValue.Venezuela()),
    Vietnam(value = CountryIntentValue.Vietnam()),
    WallisAndFutuna(value = CountryIntentValue.WallisAndFutuna()),
    WesternSahara(value = CountryIntentValue.WesternSahara()),
    Yemen(value = CountryIntentValue.Yemen()),
    Zambia(value = CountryIntentValue.Zambia()),
    Zimbabwe(value = CountryIntentValue.Zimbabwe()),
}

sealed class CountryIntentValue {
    abstract val value: String
    abstract val flag: String
    abstract val code: String

    data class Afghanistan(
        override val value: String = "Afghanistan",
        override val flag: String = "🇦🇫",
        override val code: String = "AF",
    ) : CountryIntentValue()

    data class AlandIslands(
        override val value: String = "Îles Åland",
        override val flag: String = "🇦🇽",
        override val code: String = "AX",
    ) : CountryIntentValue()

    data class Albania(
        override val value: String = "Albanie",
        override val flag: String = "🇦🇱",
        override val code: String = "AL",
    ) : CountryIntentValue()

    data class Algeria(
        override val value: String = "Algérie",
        override val flag: String = "🇩🇿",
        override val code: String = "DZ",
    ) : CountryIntentValue()

    data class AmericanSamoa(
        override val value: String = "Samoa américaines",
        override val flag: String = "🇦🇸",
        override val code: String = "AS",
    ) : CountryIntentValue()

    data class Andorra(
        override val value: String = "Andorre",
        override val flag: String = "🇦🇩",
        override val code: String = "AD",
    ) : CountryIntentValue()

    data class Angola(
        override val value: String = "Angola",
        override val flag: String = "🇦🇴",
        override val code: String = "AO",
    ) : CountryIntentValue()

    data class Anguilla(
        override val value: String = "Anguilla",
        override val flag: String = "🇦🇮",
        override val code: String = "AI",
    ) : CountryIntentValue()

    data class Antarctica(
        override val value: String = "Antarctique",
        override val flag: String = "🇦🇶",
        override val code: String = "AQ",
    ) : CountryIntentValue()

    data class AntiguaAndBarbuda(
        override val value: String = "Antigua-et-Barbuda",
        override val flag: String = "🇦🇬",
        override val code: String = "AG",
    ) : CountryIntentValue()

    data class Argentina(
        override val value: String = "Argentine",
        override val flag: String = "🇦🇷",
        override val code: String = "AR",
    ) : CountryIntentValue()

    data class Armenia(
        override val value: String = "Arménie",
        override val flag: String = "🇦🇲",
        override val code: String = "AM",
    ) : CountryIntentValue()

    data class Aruba(
        override val value: String = "Aruba",
        override val flag: String = "🇦🇼",
        override val code: String = "AW",
    ) : CountryIntentValue()

    data class Australia(
        override val value: String = "Australie",
        override val flag: String = "🇦🇺",
        override val code: String = "AU",
    ) : CountryIntentValue()

    data class Austria(
        override val value: String = "Autriche",
        override val flag: String = "🇦🇹",
        override val code: String = "AT",
    ) : CountryIntentValue()

    data class Azerbaijan(
        override val value: String = "Azerbaïdjan",
        override val flag: String = "🇦🇿",
        override val code: String = "AZ",
    ) : CountryIntentValue()

    data class Bahamas(
        override val value: String = "Bahamas",
        override val flag: String = "🇧🇸",
        override val code: String = "BS",
    ) : CountryIntentValue()

    data class Bahrain(
        override val value: String = "Bahreïn",
        override val flag: String = "🇧🇭",
        override val code: String = "BH",
    ) : CountryIntentValue()

    data class Bangladesh(
        override val value: String = "Bangladesh",
        override val flag: String = "🇧🇩",
        override val code: String = "BD",
    ) : CountryIntentValue()

    data class Barbados(
        override val value: String = "Barbade",
        override val flag: String = "🇧🇧",
        override val code: String = "BB",
    ) : CountryIntentValue()

    data class Belarus(
        override val value: String = "Biélorussie",
        override val flag: String = "🇧🇾",
        override val code: String = "BY",
    ) : CountryIntentValue()

    data class Belgium(
        override val value: String = "Belgique",
        override val flag: String = "🇧🇪",
        override val code: String = "BE",
    ) : CountryIntentValue()

    data class Belize(
        override val value: String = "Belize",
        override val flag: String = "🇧🇿",
        override val code: String = "BZ",
    ) : CountryIntentValue()

    data class Benin(
        override val value: String = "Bénin",
        override val flag: String = "🇧🇯",
        override val code: String = "BJ",
    ) : CountryIntentValue()

    data class Bermuda(
        override val value: String = "Bermudes",
        override val flag: String = "🇧🇲",
        override val code: String = "BM",
    ) : CountryIntentValue()

    data class Bhutan(
        override val value: String = "Bhoutan",
        override val flag: String = "🇧🇹",
        override val code: String = "BT",
    ) : CountryIntentValue()

    data class Bolivia(
        override val value: String = "Bolivie",
        override val flag: String = "🇧🇴",
        override val code: String = "BO",
    ) : CountryIntentValue()

    data class BonaireSaintEustacheAndSaba(
        override val value: String = "Bonaire, Saint-Eustache et Saba",
        override val flag: String = "🇧🇶",
        override val code: String = "BQ",
    ): CountryIntentValue()
    
    data class BosniaAndHerzegovina(
        override val value: String = "Bosnie-Herzégovine",
        override val flag: String = "🇧🇦",
        override val code: String = "BA",
    ) : CountryIntentValue()

    data class Botswana(
        override val value: String = "Botswana",
        override val flag: String = "🇧🇼",
        override val code: String = "BW",
    ) : CountryIntentValue()

    data class BouvetIsland(
        override val value: String = "Île Bouvet",
        override val flag: String = "🇧🇻",
        override val code: String = "BV",
    ) : CountryIntentValue()

    data class Brazil(
        override val value: String = "Brésil",
        override val flag: String = "🇧🇷",
        override val code: String = "BR",
    ) : CountryIntentValue()

    data class BritishIndianOceanTerritory(
        override val value: String = "Territoire britannique de l'océan Indien",
        override val flag: String = "🇮🇴",
        override val code: String = "IO",
    ) : CountryIntentValue()

    data class BritishVirginIslands(
        override val value: String = "Îles Vierges britanniques",
        override val flag: String = "🇻🇬",
        override val code: String = "VG",
    ) : CountryIntentValue()

    data class Bulgaria(
        override val value: String = "Bulgarie",
        override val flag: String = "🇧🇬",
        override val code: String = "BG",
    ) : CountryIntentValue()

    data class BurkinaFaso(
        override val value: String = "Burkina Faso",
        override val flag: String = "🇧🇫",
        override val code: String = "BF",
    ) : CountryIntentValue()

    data class Burundi(
        override val value: String = "Burundi",
        override val flag: String = "🇧🇮",
        override val code: String = "BI",
    ) : CountryIntentValue()

    data class Cambodia(
        override val value: String = "Cambodge",
        override val flag: String = "🇰🇭",
        override val code: String = "KH",
    ) : CountryIntentValue()

    data class Cameroon(
        override val value: String = "Cameroun",
        override val flag: String = "🇨🇲",
        override val code: String = "CM",
    ) : CountryIntentValue()

    data class Canada(
        override val value: String = "Canada",
        override val flag: String = "🇨🇦",
        override val code: String = "CA",
    ) : CountryIntentValue()

    data class CapeVerde(
        override val value: String = "Cap-Vert",
        override val flag: String = "🇨🇻",
        override val code: String = "CV",
    ) : CountryIntentValue()

    data class CaymanIslands(
        override val value: String = "Îles Caïmans",
        override val flag: String = "🇰🇾",
        override val code: String = "KY",
    ) : CountryIntentValue()

    data class CentralAfricanRepublic(
        override val value: String = "République centrafricaine",
        override val flag: String = "🇨🇫",
        override val code: String = "CF",
    ) : CountryIntentValue()

    data class Chad(
        override val value: String = "Tchad",
        override val flag: String = "🇹🇩",
        override val code: String = "TD",
    ) : CountryIntentValue()

    data class Chile(
        override val value: String = "Chili",
        override val flag: String = "🇨🇱",
        override val code: String = "CL",
    ) : CountryIntentValue()

    data class China(
        override val value: String = "Chine",
        override val flag: String = "🇨🇳",
        override val code: String = "CN",
    ) : CountryIntentValue()

    data class ChristmasIsland(
        override val value: String = "Île Christmas",
        override val flag: String = "🇨🇽",
        override val code: String = "CX",
    ) : CountryIntentValue()

    data class CocosIslands(
        override val value: String = "Îles Cocos",
        override val flag: String = "🇨🇨",
        override val code: String = "CC",
    ) : CountryIntentValue()

    data class Comoros(
        override val value: String = "Comores",
        override val flag: String = "🇰🇲",
        override val code: String = "KM",
    ) : CountryIntentValue()

    data class CongoBrazzaville(
        override val value: String = "Congo-Brazzaville",
        override val flag: String = "🇨🇬",
        override val code: String = "CG",
    ) : CountryIntentValue()

    data class CongoKinshasa(
        override val value: String = "Congo-Kinshasa",
        override val flag: String = "🇨🇩",
        override val code: String = "CD",
    ) : CountryIntentValue()

    data class CookIslands(
        override val value: String = "Îles Cook",
        override val flag: String = "🇨🇰",
        override val code: String = "CK",
    ) : CountryIntentValue()

    data class CostaRica(
        override val value: String = "Costa Rica",
        override val flag: String = "🇨🇷",
        override val code: String = "CR",
    ) : CountryIntentValue()

    data class Curacao(
        override val value: String = "Curaçao",
        override val flag: String = "🇨🇼",
        override val code: String = "CW",
    ) : CountryIntentValue()

    data class Cyprus(
        override val value: String = "Chypre",
        override val flag: String = "🇨🇾",
        override val code: String = "CY",
    ) : CountryIntentValue()

    data class Czechia(
        override val value: String = "Tchéquie",
        override val flag: String = "🇨🇿",
        override val code: String = "CZ",
    ) : CountryIntentValue()

    data class Denmark(
        override val value: String = "Danemark",
        override val flag: String = "🇩🇰",
        override val code: String = "DK",
    ) : CountryIntentValue()

    data class Djibouti(
        override val value: String = "Djibouti",
        override val flag: String = "🇩🇯",
        override val code: String = "DJ",
    ) : CountryIntentValue()

    data class Dominica(
        override val value: String = "Dominique",
        override val flag: String = "🇩🇲",
        override val code: String = "DM",
    ) : CountryIntentValue()

    data class DominicanRepublic(
        override val value: String = "République dominicaine",
        override val flag: String = "🇩🇴",
        override val code: String = "DO",
    ) : CountryIntentValue()

    data class EastTimor(
        override val value: String = "Timor oriental",
        override val flag: String = "🇹🇱",
        override val code: String = "TL",
    ) : CountryIntentValue()

    data class Ecuador(
        override val value: String = "Équateur",
        override val flag: String = "🇪🇨",
        override val code: String = "EC",
    ) : CountryIntentValue()

    data class Egypt(
        override val value: String = "Égypte",
        override val flag: String = "🇪🇬",
        override val code: String = "EG",
    ) : CountryIntentValue()

    data class ElSalvador(
        override val value: String = "Salvador",
        override val flag: String = "🇸🇻",
        override val code: String = "SV",
    ) : CountryIntentValue()

    data class EquatorialGuinea(
        override val value: String = "Guinée équatoriale",
        override val flag: String = "🇬🇶",
        override val code: String = "GQ",
    ) : CountryIntentValue()

    data class Eritrea(
        override val value: String = "Érythrée",
        override val flag: String = "🇪🇷",
        override val code: String = "ER",
    ) : CountryIntentValue()

    data class Estonia(
        override val value: String = "Estonie",
        override val flag: String = "🇪🇪",
        override val code: String = "EE",
    ) : CountryIntentValue()

    data class Eswatini(
        override val value: String = "Eswatini",
        override val flag: String = "🇸🇿",
        override val code: String = "SZ",
    ) : CountryIntentValue()

    data class Ethiopia(
        override val value: String = "Éthiopie",
        override val flag: String = "🇪🇹",
        override val code: String = "ET",
    ) : CountryIntentValue()

    data class FalklandIslands(
        override val value: String = "Îles Malouines",
        override val flag: String = "🇫🇰",
        override val code: String = "FK",
    ) : CountryIntentValue()

    data class FaroeIslands(
        override val value: String = "Îles Féroé",
        override val flag: String = "🇫🇴",
        override val code: String = "FO",
    ) : CountryIntentValue()

    data class Fiji(
        override val value: String = "Fidji",
        override val flag: String = "🇫🇯",
        override val code: String = "FJ",
    ) : CountryIntentValue()

    data class Finland(
        override val value: String = "Finlande",
        override val flag: String = "🇫🇮",
        override val code: String = "FI",
    ) : CountryIntentValue()

    data class France(
        override val value: String = "France",
        override val flag: String = "🇫🇷",
        override val code: String = "FR",
    ) : CountryIntentValue()

    data class FrenchGuiana(
        override val value: String = "Guyane française",
        override val flag: String = "🇬🇫",
        override val code: String = "GF",
    ) : CountryIntentValue()

    data class FrenchPolynesia(
        override val value: String = "Polynésie française",
        override val flag: String = "🇵🇫",
        override val code: String = "PF",
    ) : CountryIntentValue()

    data class FrenchSouthernTerritories(
        override val value: String = "Terres australes françaises",
        override val flag: String = "🇹🇫",
        override val code: String = "TF",
    ) : CountryIntentValue()

    data class Gabon(
        override val value: String = "Gabon",
        override val flag: String = "🇬🇦",
        override val code: String = "GA",
    ) : CountryIntentValue()

    data class Gambia(
        override val value: String = "Gambie",
        override val flag: String = "🇬🇲",
        override val code: String = "GM",
    ) : CountryIntentValue()

    data class Georgia(
        override val value: String = "Géorgie",
        override val flag: String = "🇬🇪",
        override val code: String = "GE",
    ) : CountryIntentValue()

    data class Germany(
        override val value: String = "Allemagne",
        override val flag: String = "🇩🇪",
        override val code: String = "DE",
    ) : CountryIntentValue()

    data class Ghana(
        override val value: String = "Ghana",
        override val flag: String = "🇬🇭",
        override val code: String = "GH",
    ) : CountryIntentValue()

    data class Gibraltar(
        override val value: String = "Gibraltar",
        override val flag: String = "🇬🇮",
        override val code: String = "GI",
    ) : CountryIntentValue()

    data class Greece(
        override val value: String = "Grèce",
        override val flag: String = "🇬🇷",
        override val code: String = "GR",
    ) : CountryIntentValue()

    data class Greenland(
        override val value: String = "Groenland",
        override val flag: String = "🇬🇱",
        override val code: String = "GL",
    ) : CountryIntentValue()

    data class Grenada(
        override val value: String = "Grenade",
        override val flag: String = "🇬🇩",
        override val code: String = "GD",
    ) : CountryIntentValue()

    data class Guadeloupe(
        override val value: String = "Guadeloupe",
        override val flag: String = "🇬🇵",
        override val code: String = "GP",
    ) : CountryIntentValue()

    data class Guam(
        override val value: String = "Guam",
        override val flag: String = "🇬🇺",
        override val code: String = "GU",
    ) : CountryIntentValue()

    data class Guatemala(
        override val value: String = "Guatemala",
        override val flag: String = "🇬🇹",
        override val code: String = "GT",
    ) : CountryIntentValue()

    data class Guernsey(
        override val value: String = "Guernesey",
        override val flag: String = "🇬🇬",
        override val code: String = "GG",
    ) : CountryIntentValue()

    data class Guinea(
        override val value: String = "Guinée",
        override val flag: String = "🇬🇳",
        override val code: String = "GN",
    ) : CountryIntentValue()

    data class GuineaBissau(
        override val value: String = "Guinée-Bissau",
        override val flag: String = "🇬🇼",
        override val code: String = "GW",
    ) : CountryIntentValue()

    data class Guyana(
        override val value: String = "Guyana",
        override val flag: String = "🇬🇾",
        override val code: String = "GY",
    ) : CountryIntentValue()

    data class Haiti(
        override val value: String = "Haïti",
        override val flag: String = "🇭🇹",
        override val code: String = "HT",
    ) : CountryIntentValue()

    data class HeardAndMcDonaldIslands(
        override val value: String = "Îles Heard-et-MacDonald",
        override val flag: String = "🇭🇲",
        override val code: String = "HM",
    ) : CountryIntentValue()

    data class Honduras(
        override val value: String = "Honduras",
        override val flag: String = "🇭🇳",
        override val code: String = "HN",
    ) : CountryIntentValue()

    data class HongKongSAR(
        override val value: String = "R.A.S. chinoise de Hong Kong",
        override val flag: String = "🇭🇰",
        override val code: String = "HK",
    ) : CountryIntentValue()

    data class Hungary(
        override val value: String = "Hongrie",
        override val flag: String = "🇭🇺",
        override val code: String = "HU",
    ): CountryIntentValue()

    data class Iceland(
        override val value: String = "Islande",
        override val flag: String = "🇮🇸",
        override val code: String = "IS",
    ): CountryIntentValue()

    data class India(
        override val value: String = "Inde",
        override val flag: String = "🇮🇳",
        override val code: String = "IN",
    ): CountryIntentValue()

    data class Indonesia(
        override val value: String = "Indonésie",
        override val flag: String = "🇮🇩",
        override val code: String = "ID",
    ): CountryIntentValue()

    data class Iran(
        override val value: String = "Iran",
        override val flag: String = "🇮🇷",
        override val code: String = "IR",
    ): CountryIntentValue()

    data class Iraq(
        override val value: String = "Irak",
        override val flag: String = "🇮🇶",
        override val code: String = "IQ",
    ): CountryIntentValue()

    data class Ireland(
        override val value: String = "Irlande",
        override val flag: String = "🇮🇪",
        override val code: String = "IE",
    ): CountryIntentValue()

    data class IsleOfMan(
        override val value: String = "Île de Man",
        override val flag: String = "🇮🇲",
        override val code: String = "IM",
    ): CountryIntentValue()

    data class Israel(
        override val value: String = "Israël",
        override val flag: String = "🇮🇱",
        override val code: String = "IL",
    ): CountryIntentValue()

    data class Italy(
        override val value: String = "Italie",
        override val flag: String = "🇮🇹",
        override val code: String = "IT",
    ): CountryIntentValue()

    data class Jamaica(
        override val value: String = "Jamaïque",
        override val flag: String = "🇯🇲",
        override val code: String = "JM",
    ): CountryIntentValue()

    data class Japan(
        override val value: String = "Japon",
        override val flag: String = "🇯🇵",
        override val code: String = "JP",
    ): CountryIntentValue()

    data class Jersey(
        override val value: String = "Jersey",
        override val flag: String = "🇯🇪",
        override val code: String = "JE",
    ): CountryIntentValue()

    data class Jordan(
        override val value: String = "Jordanie",
        override val flag: String = "🇯🇴",
        override val code: String = "JO",
    ): CountryIntentValue()

    data class Kazakhstan(
        override val value: String = "Kazakhstan",
        override val flag: String = "🇰🇿",
        override val code: String = "KZ",
    ): CountryIntentValue()

    data class Kenya(
        override val value: String = "Kenya",
        override val flag: String = "🇰🇪",
        override val code: String = "KE",
    ): CountryIntentValue()

    data class Kiribati(
        override val value: String = "Kiribati",
        override val flag: String = "🇰🇮",
        override val code: String = "KI",
    ): CountryIntentValue()

    data class Kosovo(
        override val value: String = "Kosovo",
        override val flag: String = "🇽🇰",
        override val code: String = "XK",
    ): CountryIntentValue()

    data class Kuwait(
        override val value: String = "Koweït",
        override val flag: String = "🇰🇼",
        override val code: String = "KW",
    ): CountryIntentValue()

    data class Kyrgyzstan(
        override val value: String = "Kirghizstan",
        override val flag: String = "🇰🇬",
        override val code: String = "KG",
    ): CountryIntentValue()

    data class Laos(
        override val value: String = "Laos",
        override val flag: String = "🇱🇦",
        override val code: String = "LA",
    ): CountryIntentValue()

    data class Latvia(
        override val value: String = "Lettonie",
        override val flag: String = "🇱🇻",
        override val code: String = "LV",
    ): CountryIntentValue()

    data class Lebanon(
        override val value: String = "Liban",
        override val flag: String = "🇱🇧",
        override val code: String = "LB",
    ): CountryIntentValue()

    data class Lesotho(
        override val value: String = "Lesotho",
        override val flag: String = "🇱🇸",
        override val code: String = "LS",
    ): CountryIntentValue()

    data class Liberia(
        override val value: String = "Liberia",
        override val flag: String = "🇱🇷",
        override val code: String = "LR",
    ): CountryIntentValue()

    data class Libya(
        override val value: String = "Libye",
        override val flag: String = "🇱🇾",
        override val code: String = "LY",
    ): CountryIntentValue()

    data class Liechtenstein(
        override val value: String = "Liechtenstein",
        override val flag: String = "🇱🇮",
        override val code: String = "LI",
    ): CountryIntentValue()

    data class Lithuania(
        override val value: String = "Lituanie",
        override val flag: String = "🇱🇹",
        override val code: String = "LT",
    ): CountryIntentValue()

    data class Luxembourg(
        override val value: String = "Luxembourg",
        override val flag: String = "🇱🇺",
        override val code: String = "LU",
    ): CountryIntentValue()


    data class MacaoSAR(
        override val value: String = "R.A.S. chinoise de Macao",
        override val flag: String = "🇲🇴",
        override val code: String = "MO",
    ) : CountryIntentValue()

    data class Macedonia(
        override val value: String = "Macédoine du Nord",
        override val flag: String = "🇲🇰",
        override val code: String = "MK",
    ) : CountryIntentValue()

    data class Madagascar(
        override val value: String = "Madagascar",
        override val flag: String = "🇲🇬",
        override val code: String = "MG",
    ) : CountryIntentValue()

    data class Malawi(
        override val value: String = "Malawi",
        override val flag: String = "🇲🇼",
        override val code: String = "MW",
    ) : CountryIntentValue()

    data class Malaysia(
        override val value: String = "Malaisie",
        override val flag: String = "🇲🇾",
        override val code: String = "MY",
    ) : CountryIntentValue()

    data class Maldives(
        override val value: String = "Maldives",
        override val flag: String = "🇲🇻",
        override val code: String = "MV",
    ) : CountryIntentValue()

    data class Mali(
        override val value: String = "Mali",
        override val flag: String = "🇲🇱",
        override val code: String = "ML",
    ) : CountryIntentValue()

    data class Malta(
        override val value: String = "Malte",
        override val flag: String = "🇲🇹",
        override val code: String = "MT",
    ) : CountryIntentValue()

    data class MarshallIslands(
        override val value: String = "Îles Marshall",
        override val flag: String = "🇲🇭",
        override val code: String = "MH",
    ) : CountryIntentValue()

    data class Martinique(
        override val value: String = "Martinique",
        override val flag: String = "🇲🇶",
        override val code: String = "MQ",
    ) : CountryIntentValue()

    data class Mauritania(
        override val value: String = "Mauritanie",
        override val flag: String = "🇲🇷",
        override val code: String = "MR",
    ) : CountryIntentValue()

    data class Mauritius(
        override val value: String = "Maurice",
        override val flag: String = "🇲🇺",
        override val code: String = "MU",
    ) : CountryIntentValue()

    data class Mayotte(
        override val value: String = "Mayotte",
        override val flag: String = "🇾🇹",
        override val code: String = "YT",
    ) : CountryIntentValue()

    data class Mexico(
        override val value: String = "Mexique",
        override val flag: String = "🇲🇽",
        override val code: String = "MX",
    ) : CountryIntentValue()

    data class Micronesia(
        override val value: String = "Micronésie",
        override val flag: String = "🇫🇲",
        override val code: String = "FM",
    ) : CountryIntentValue()

    data class Moldova(
        override val value: String = "Moldavie",
        override val flag: String = "🇲🇩",
        override val code: String = "MD",
    ) : CountryIntentValue()

    data class Monaco(
        override val value: String = "Monaco",
        override val flag: String = "🇲🇨",
        override val code: String = "MC",
    ) : CountryIntentValue()

    data class Mongolia(
        override val value: String = "Mongolie",
        override val flag: String = "🇲🇳",
        override val code: String = "MN",
    ) : CountryIntentValue()

    data class Montenegro(
        override val value: String = "Monténégro",
        override val flag: String = "🇲🇪",
        override val code: String = "ME",
    ) : CountryIntentValue()

    data class Montserrat(
        override val value: String = "Montserrat",
        override val flag: String = "🇲🇸",
        override val code: String = "MS",
    ) : CountryIntentValue()

    data class Morocco(
        override val value: String = "Maroc",
        override val flag: String = "🇲🇦",
        override val code: String = "MA",
    ) : CountryIntentValue()

    data class Mozambique(
        override val value: String = "Mozambique",
        override val flag: String = "🇲🇿",
        override val code: String = "MZ",
    ) : CountryIntentValue()

    data class Myanmar(
        override val value: String = "Myanmar (Birmanie)",
        override val flag: String = "🇲🇲",
        override val code: String = "MM",
    ) : CountryIntentValue()

    data class Namibia(
        override val value: String = "Namibie",
        override val flag: String = "🇳🇦",
        override val code: String = "NA",
    ) : CountryIntentValue()

    data class Nauru(
        override val value: String = "Nauru",
        override val flag: String = "🇳🇷",
        override val code: String = "NR",
    ) : CountryIntentValue()

    data class Nepal(
        override val value: String = "Népal",
        override val flag: String = "🇳🇵",
        override val code: String = "NP",
    ) : CountryIntentValue()

    data class Netherlands(
        override val value: String = "Pays-Bas",
        override val flag: String = "🇳🇱",
        override val code: String = "NL",
    ) : CountryIntentValue()

    data class NetherlandsAntilles(
        override val value: String = "Pays-Bas caribéens",
        override val flag: String = "🇧🇶",
        override val code: String = "AN",
    ) : CountryIntentValue()

    data class NewCaledonia(
        override val value: String = "Nouvelle-Calédonie",
        override val flag: String = "🇳🇨",
        override val code: String = "NC",
    ) : CountryIntentValue()

    data class NewZealand(
        override val value: String = "Nouvelle-Zélande",
        override val flag: String = "🇳🇿",
        override val code: String = "NZ",
    ) : CountryIntentValue()

    data class Nicaragua(
        override val value: String = "Nicaragua",
        override val flag: String = "🇳🇮",
        override val code: String = "NI",
    ) : CountryIntentValue()

    data class Niger(
        override val value: String = "Niger",
        override val flag: String = "🇳🇪",
        override val code: String = "NE",
    ) : CountryIntentValue()

    data class Nigeria(
        override val value: String = "Nigeria",
        override val flag: String = "🇳🇬",
        override val code: String = "NG",
    ) : CountryIntentValue()

    data class Niue(
        override val value: String = "Niue",
        override val flag: String = "🇳🇺",
        override val code: String = "NU",
    ) : CountryIntentValue()

    data class NorfolkIsland(
        override val value: String = "Île Norfolk",
        override val flag: String = "🇳🇫",
        override val code: String = "NF",
    ) : CountryIntentValue()

    data class NorthKorea(
        override val value: String = "Corée du Nord",
        override val flag: String = "🇰🇵",
        override val code: String = "KP",
    ) : CountryIntentValue()

    data class NorthernMarianaIslands(
        override val value: String = "Îles Mariannes du Nord",
        override val flag: String = "🇲🇵",
        override val code: String = "MP",
    ) : CountryIntentValue()

    data class Norway(
        override val value: String = "Norvège",
        override val flag: String = "🇳🇴",
        override val code: String = "NO",
    ) : CountryIntentValue()

    data class Oman(
        override val value: String = "Oman",
        override val flag: String = "🇴🇲",
        override val code: String = "OM",
    ) : CountryIntentValue()

    data class Pakistan(
        override val value: String = "Pakistan",
        override val flag: String = "🇵🇰",
        override val code: String = "PK",
    ) : CountryIntentValue()

    data class Palau(
        override val value: String = "Palau",
        override val flag: String = "🇵🇼",
        override val code: String = "PW",
    ) : CountryIntentValue()

    data class PalestinianTerritories(
        override val value: String = "Palestine",
        override val flag: String = "🇵🇸",
        override val code: String = "PS",
    ) : CountryIntentValue()

    data class Panama(
        override val value: String = "Panama",
        override val flag: String = "🇵🇳",
        override val code: String = "PA",
    ) : CountryIntentValue()

    data class PapuaNewGuinea(
        override val value: String = "Papouasie-Nouvelle-Guinée",
        override val flag: String = "🇵🇬",
        override val code: String = "PG",
    ) : CountryIntentValue()

    data class Paraguay(
        override val value: String = "Paraguay",
        override val flag: String = "🇵🇾",
        override val code: String = "PY",
    ) : CountryIntentValue()

    data class Peru(
        override val value: String = "Pérou",
        override val flag: String = "🇵🇪",
        override val code: String = "PE",
    ) : CountryIntentValue()

    data class Philippines(
        override val value: String = "Philippines",
        override val flag: String = "🇵🇭",
        override val code: String = "PH",
    ) : CountryIntentValue()

    data class PitcairnIslands(
        override val value: String = "Îles Pitcairn",
        override val flag: String = "🇵🇳",
        override val code: String = "PN",
    ) : CountryIntentValue()

    data class Poland(
        override val value: String = "Pologne",
        override val flag: String = "🇵🇱",
        override val code: String = "PL",
    ) : CountryIntentValue()

    data class Portugal(
        override val value: String = "Portugal",
        override val flag: String = "🇵🇹",
        override val code: String = "PT",
    ) : CountryIntentValue()

    data class PuertoRico(
        override val value: String = "Porto Rico",
        override val flag: String = "🇵🇷",
        override val code: String = "PR",
    ) : CountryIntentValue()

    data class Qatar(
        override val value: String = "Qatar",
        override val flag: String = "🇶🇦",
        override val code: String = "QA",
    ) : CountryIntentValue()

    data class Reunion(
        override val value: String = "La Réunion",
        override val flag: String = "🇷🇪",
        override val code: String = "RE",
    ) : CountryIntentValue()

    data class Romania(
        override val value: String = "Roumanie",
        override val flag: String = "🇷🇴",
        override val code: String = "RO",
    ) : CountryIntentValue()

    data class Russia(
        override val value: String = "Russie",
        override val flag: String = "🇷🇺",
        override val code: String = "RU",
    ) : CountryIntentValue()

    data class Rwanda(
        override val value: String = "Rwanda",
        override val flag: String = "🇷🇼",
        override val code: String = "RW",
    ) : CountryIntentValue()

    data class SaintBarthelemy(
        override val value: String = "Saint-Barthélemy",
        override val flag: String = "🇧🇱",
        override val code: String = "BL",
    ) : CountryIntentValue()

    data class SaintHelena(
        override val value: String = "Sainte-Hélène",
        override val flag: String = "🇸🇭",
        override val code: String = "SH",
    ) : CountryIntentValue()

    data class SaintKittsAndNevis(
        override val value: String = "Saint-Christophe-et-Niévès",
        override val flag: String = "🇰🇳",
        override val code: String = "KN",
    ) : CountryIntentValue()

    data class SaintLucia(
        override val value: String = "Sainte-Lucie",
        override val flag: String = "🇱🇨",
        override val code: String = "LC",
    ) : CountryIntentValue()

    data class SaintPierreAndMiquelon(
        override val value: String = "Saint-Pierre-et-Miquelon",
        override val flag: String = "🇵🇲",
        override val code: String = "PM",
    ) : CountryIntentValue()

    data class SaintVincentAndTheGrenadines(
        override val value: String = "Saint-Vincent-et-les Grenadines",
        override val flag: String = "🇻🇨",
        override val code: String = "VC",
    ) : CountryIntentValue()

    data class Samoa(
        override val value: String = "Samoa",
        override val flag: String = "🇼🇸",
        override val code: String = "WS",
    ) : CountryIntentValue()

    data class SanMarino(
        override val value: String = "Saint-Marin",
        override val flag: String = "🇸🇲",
        override val code: String = "SM",
    ) : CountryIntentValue()

    data class SaoTomeAndPrincipe(
        override val value: String = "Sao Tomé-et-Principe",
        override val flag: String = "🇸🇹",
        override val code: String = "ST",
    ) : CountryIntentValue()

    data class SaudiArabia(
        override val value: String = "Arabie saoudite",
        override val flag: String = "🇸🇦",
        override val code: String = "SA",
    ) : CountryIntentValue()

    data class Senegal(
        override val value: String = "Sénégal",
        override val flag: String = "🇸🇳",
        override val code: String = "SN",
    ) : CountryIntentValue()

    data class Serbia(
        override val value: String = "Serbie",
        override val flag: String = "🇷🇸",
        override val code: String = "RS",
    ) : CountryIntentValue()

    data class Seychelles(
        override val value: String = "Seychelles",
        override val flag: String = "🇸🇨",
        override val code: String = "SC",
    ) : CountryIntentValue()

    data class SierraLeone(
        override val value: String = "Sierra Leone",
        override val flag: String = "🇸🇱",
        override val code: String = "SL",
    ) : CountryIntentValue()

    data class Singapore(
        override val value: String = "Singapour",
        override val flag: String = "🇸🇬",
        override val code: String = "SG",
    ) : CountryIntentValue()

    data class Slovakia(
        override val value: String = "Slovaquie",
        override val flag: String = "🇸🇰",
        override val code: String = "SK",
    ) : CountryIntentValue()

    data class Slovenia(
        override val value: String = "Slovénie",
        override val flag: String = "🇸🇮",
        override val code: String = "SI",
    ) : CountryIntentValue()

    data class SolomonIslands(
        override val value: String = "Îles Salomon",
        override val flag: String = "🇸🇧",
        override val code: String = "SB",
    ) : CountryIntentValue()

    data class Somalia(
        override val value: String = "Somalie",
        override val flag: String = "🇸🇴",
        override val code: String = "SO",
    ) : CountryIntentValue()

    data class Somaliland(
        override val value: String = "Somaliland",
        override val flag: String = "🇸🇴",
        override val code: String = "SOL",
    ) : CountryIntentValue()

    data class SouthAfrica(
        override val value: String = "Afrique du Sud",
        override val flag: String = "🇿🇦",
        override val code: String = "ZA",
    ) : CountryIntentValue()

    data class SouthKorea(
        override val value: String = "Corée du Sud",
        override val flag: String = "🇰🇷",
        override val code: String = "KR",
    ) : CountryIntentValue()

    data class SouthSudan(
        override val value: String = "Soudan du Sud",
        override val flag: String = "🇸🇸",
        override val code: String = "SS",
    ) : CountryIntentValue()

    data class Spain(
        override val value: String = "Espagne",
        override val flag: String = "🇪🇸",
        override val code: String = "ES",
    ) : CountryIntentValue()

    data class SriLanka(
        override val value: String = "Sri Lanka",
        override val flag: String = "🇱🇰",
        override val code: String = "LK",
    ) : CountryIntentValue()

    data class Sudan(
        override val value: String = "Soudan",
        override val flag: String = "🇸🇩",
        override val code: String = "SD",
    ) : CountryIntentValue()

    data class Suriname(
        override val value: String = "Suriname",
        override val flag: String = "🇸🇷",
        override val code: String = "SR",
    ) : CountryIntentValue()

    data class SvalbardAndJanMayen(
        override val value: String = "Svalbard et Jan Mayen",
        override val flag: String = "🇸🇯",
        override val code: String = "SJ",
    ) : CountryIntentValue()

    data class Swaziland(
        override val value: String = "Swaziland",
        override val flag: String = "🇸🇿",
        override val code: String = "SWZ",
    ) : CountryIntentValue()

    data class Sweden(
        override val value: String = "Suède",
        override val flag: String = "🇸🇪",
        override val code: String = "SE",
    ) : CountryIntentValue()

    data class Switzerland(
        override val value: String = "Suisse",
        override val flag: String = "🇨🇭",
        override val code: String = "CH",
    ) : CountryIntentValue()

    data class Syria(
        override val value: String = "Syrie",
        override val flag: String = "🇸🇾",
        override val code: String = "SY",
    ) : CountryIntentValue()

    data class Taiwan(
        override val value: String = "Taïwan",
        override val flag: String = "🇹🇼",
        override val code: String = "TW",
    ) : CountryIntentValue()

    data class Tajikistan(
        override val value: String = "Tadjikistan",
        override val flag: String = "🇹🇯",
        override val code: String = "TJ",
    ) : CountryIntentValue()

    data class Tanzania(
        override val value: String = "Tanzanie",
        override val flag: String = "🇹🇿",
        override val code: String = "TZ",
    ) : CountryIntentValue()

    data class Thailand(
        override val value: String = "Thaïlande",
        override val flag: String = "🇹🇭",
        override val code: String = "TH",
    ) : CountryIntentValue()

    data class Togo(
        override val value: String = "Togo",
        override val flag: String = "🇹🇬",
        override val code: String = "TG",
    ) : CountryIntentValue()

    data class Tokelau(
        override val value: String = "Tokelau",
        override val flag: String = "🇹🇰",
        override val code: String = "TK",
    ) : CountryIntentValue()

    data class Tonga(
        override val value: String = "Tonga",
        override val flag: String = "🇹🇴",
        override val code: String = "TO",
    ) : CountryIntentValue()

    data class Transnistrie(
        override val value: String = "Transnistrie",
        override val flag: String = "🇲🇩",
        override val code: String = "PMR",
    ) : CountryIntentValue()

    data class TrinidadAndTobago(
        override val value: String = "Trinité-et-Tobago",
        override val flag: String = "🇹🇹",
        override val code: String = "TT",
    ) : CountryIntentValue()

    data class Tunisia(
        override val value: String = "Tunisie",
        override val flag: String = "🇹🇳",
        override val code: String = "TN",
    ) : CountryIntentValue()

    data class Turkey(
        override val value: String = "Turquie",
        override val flag: String = "🇹🇷",
        override val code: String = "TR",
    ) : CountryIntentValue()

    data class Turkmenistan(
        override val value: String = "Turkménistan",
        override val flag: String = "🇹🇲",
        override val code: String = "TM",
    ) : CountryIntentValue()

    data class TurksAndCaicosIslands(
        override val value: String = "Îles Turques-et-Caïques",
        override val flag: String = "🇹🇨",
        override val code: String = "TC",
    ) : CountryIntentValue()

    data class Tuvalu(
        override val value: String = "Tuvalu",
        override val flag: String = "🇹🇻",
        override val code: String = "TV",
    ) : CountryIntentValue()

    data class Uganda(
        override val value: String = "Ouganda",
        override val flag: String = "🇺🇬",
        override val code: String = "UG",
    ) : CountryIntentValue()

    data class Ukraine(
        override val value: String = "Ukraine",
        override val flag: String = "🇺🇦",
        override val code: String = "UA",
    ) : CountryIntentValue()

    data class UnitedArabEmirates(
        override val value: String = "Émirats arabes unis",
        override val flag: String = "🇦🇪",
        override val code: String = "AE",
    ) : CountryIntentValue()

    data class UnitedKingdom(
        override val value: String = "Royaume-Uni",
        override val flag: String = "🇬🇧",
        override val code: String = "GB",
    ) : CountryIntentValue()

    data class UnitedStates(
        override val value: String = "États-Unis",
        override val flag: String = "🇺🇸",
        override val code: String = "US",
    ) : CountryIntentValue()

    data class USOutlyingIslands(
        override val value: String = "Îles mineures éloignées des États-Unis",
        override val flag: String = "🇺🇲",
        override val code: String = "UM",
    ) : CountryIntentValue()

    data class USVirginIslands(
        override val value: String = "Îles Vierges des États-Unis",
        override val flag: String = "🇻🇮",
        override val code: String = "VI",
    ) : CountryIntentValue()

    data class Uruguay(
        override val value: String = "Uruguay",
        override val flag: String = "🇺🇾",
        override val code: String = "UY",
    ) : CountryIntentValue()

    data class Uzbekistan(
        override val value: String = "Ouzbékistan",
        override val flag: String = "🇺🇿",
        override val code: String = "UZ",
    ) : CountryIntentValue()

    data class Vanuatu(
        override val value: String = "Vanuatu",
        override val flag: String = "🇻🇺",
        override val code: String = "VU",
    ) : CountryIntentValue()

    data class VaticanCityState(
        override val value: String = "État de la Cité du Vatican",
        override val flag: String = "🇻🇦",
        override val code: String = "VA",
    ) : CountryIntentValue()

    data class Venezuela(
        override val value: String = "Venezuela",
        override val flag: String = "🇻🇪",
        override val code: String = "VE",
    ) : CountryIntentValue()

    data class Vietnam(
        override val value: String = "Viêt Nam",
        override val flag: String = "🇻🇳",
        override val code: String = "VN",
    ) : CountryIntentValue()

    data class WallisAndFutuna(
        override val value: String = "Wallis-et-Futuna",
        override val flag: String = "🇼🇫",
        override val code: String = "WF",
    ) : CountryIntentValue()

    data class WesternSahara(
        override val value: String = "Sahara occidental",
        override val flag: String = "🇪🇭",
        override val code: String = "EH",
    ) : CountryIntentValue()

    data class Yemen(
        override val value: String = "Yémen",
        override val flag: String = "🇾🇪",
        override val code: String = "YE",
    ) : CountryIntentValue()

    data class Zambia(
        override val value: String = "Zambie",
        override val flag: String = "🇿🇲",
        override val code: String = "ZM",
    ) : CountryIntentValue()

    data class Zimbabwe(
        override val value: String = "Zimbabwe",
        override val flag: String = "🇿🇼",
        override val code: String = "ZW",
    ) : CountryIntentValue()
}
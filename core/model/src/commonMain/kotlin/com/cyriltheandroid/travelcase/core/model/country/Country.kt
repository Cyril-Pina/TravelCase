package com.cyriltheandroid.travelcase.core.model.country

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("Country", exact = true)
sealed class Country {
    abstract val value: String
    abstract val flag: String
    abstract val code: String

    data class Afghanistan(
        override val value: String = "Afghanistan",
        override val flag: String = "🇦🇫",
        override val code: String = "AF",
    ) : Country()

    data class AlandIslands(
        override val value: String = "Îles Åland",
        override val flag: String = "🇦🇽",
        override val code: String = "AX",
    ) : Country()

    data class Albania(
        override val value: String = "Albanie",
        override val flag: String = "🇦🇱",
        override val code: String = "AL",
    ) : Country()

    data class Algeria(
        override val value: String = "Algérie",
        override val flag: String = "🇩🇿",
        override val code: String = "DZ",
    ) : Country()

    data class AmericanSamoa(
        override val value: String = "Samoa américaines",
        override val flag: String = "🇦🇸",
        override val code: String = "AS",
    ) : Country()

    data class Andorra(
        override val value: String = "Andorre",
        override val flag: String = "🇦🇩",
        override val code: String = "AD",
    ) : Country()

    data class Angola(
        override val value: String = "Angola",
        override val flag: String = "🇦🇴",
        override val code: String = "AO",
    ) : Country()

    data class Anguilla(
        override val value: String = "Anguilla",
        override val flag: String = "🇦🇮",
        override val code: String = "AI",
    ) : Country()

    data class Antarctica(
        override val value: String = "Antarctique",
        override val flag: String = "🇦🇶",
        override val code: String = "AQ",
    ) : Country()

    data class AntiguaAndBarbuda(
        override val value: String = "Antigua-et-Barbuda",
        override val flag: String = "🇦🇬",
        override val code: String = "AG",
    ) : Country()

    data class Argentina(
        override val value: String = "Argentine",
        override val flag: String = "🇦🇷",
        override val code: String = "AR",
    ) : Country()

    data class Armenia(
        override val value: String = "Arménie",
        override val flag: String = "🇦🇲",
        override val code: String = "AM",
    ) : Country()

    data class Aruba(
        override val value: String = "Aruba",
        override val flag: String = "🇦🇼",
        override val code: String = "AW",
    ) : Country()

    data class Australia(
        override val value: String = "Australie",
        override val flag: String = "🇦🇺",
        override val code: String = "AU",
    ) : Country()

    data class Austria(
        override val value: String = "Autriche",
        override val flag: String = "🇦🇹",
        override val code: String = "AT",
    ) : Country()

    data class Azerbaijan(
        override val value: String = "Azerbaïdjan",
        override val flag: String = "🇦🇿",
        override val code: String = "AZ",
    ) : Country()

    data class Bahamas(
        override val value: String = "Bahamas",
        override val flag: String = "🇧🇸",
        override val code: String = "BS",
    ) : Country()

    data class Bahrain(
        override val value: String = "Bahreïn",
        override val flag: String = "🇧🇭",
        override val code: String = "BH",
    ) : Country()

    data class Bangladesh(
        override val value: String = "Bangladesh",
        override val flag: String = "🇧🇩",
        override val code: String = "BD",
    ) : Country()

    data class Barbados(
        override val value: String = "Barbade",
        override val flag: String = "🇧🇧",
        override val code: String = "BB",
    ) : Country()

    data class Belarus(
        override val value: String = "Biélorussie",
        override val flag: String = "🇧🇾",
        override val code: String = "BY",
    ) : Country()

    data class Belgium(
        override val value: String = "Belgique",
        override val flag: String = "🇧🇪",
        override val code: String = "BE",
    ) : Country()

    data class Belize(
        override val value: String = "Belize",
        override val flag: String = "🇧🇿",
        override val code: String = "BZ",
    ) : Country()

    data class Benin(
        override val value: String = "Bénin",
        override val flag: String = "🇧🇯",
        override val code: String = "BJ",
    ) : Country()

    data class BonaireSaintEustacheAndSaba(
        override val value: String = "Bonaire, Saint-Eustache et Saba",
        override val flag: String = "🇧🇶",
        override val code: String = "BQ",
    ) : Country()

    data class Bermuda(
        override val value: String = "Bermudes",
        override val flag: String = "🇧🇲",
        override val code: String = "BM",
    ) : Country()

    data class Bhutan(
        override val value: String = "Bhoutan",
        override val flag: String = "🇧🇹",
        override val code: String = "BT",
    ) : Country()

    data class Bolivia(
        override val value: String = "Bolivie",
        override val flag: String = "🇧🇴",
        override val code: String = "BO",
    ) : Country()

    data class BosniaAndHerzegovina(
        override val value: String = "Bosnie-Herzégovine",
        override val flag: String = "🇧🇦",
        override val code: String = "BA",
    ) : Country()

    data class Botswana(
        override val value: String = "Botswana",
        override val flag: String = "🇧🇼",
        override val code: String = "BW",
    ) : Country()

    data class BouvetIsland(
        override val value: String = "Île Bouvet",
        override val flag: String = "🇧🇻",
        override val code: String = "BV",
    ) : Country()

    data class Brazil(
        override val value: String = "Brésil",
        override val flag: String = "🇧🇷",
        override val code: String = "BR",
    ) : Country()

    data class BritishIndianOceanTerritory(
        override val value: String = "Territoire britannique de l'océan Indien",
        override val flag: String = "🇮🇴",
        override val code: String = "IO",
    ) : Country()

    data class BritishVirginIslands(
        override val value: String = "Îles Vierges britanniques",
        override val flag: String = "🇻🇬",
        override val code: String = "VG",
    ) : Country()

    data class Bulgaria(
        override val value: String = "Bulgarie",
        override val flag: String = "🇧🇬",
        override val code: String = "BG",
    ) : Country()

    data class BurkinaFaso(
        override val value: String = "Burkina Faso",
        override val flag: String = "🇧🇫",
        override val code: String = "BF",
    ) : Country()

    data class Burundi(
        override val value: String = "Burundi",
        override val flag: String = "🇧🇮",
        override val code: String = "BI",
    ) : Country()

    data class Cambodia(
        override val value: String = "Cambodge",
        override val flag: String = "🇰🇭",
        override val code: String = "KH",
    ) : Country()

    data class Cameroon(
        override val value: String = "Cameroun",
        override val flag: String = "🇨🇲",
        override val code: String = "CM",
    ) : Country()

    data class Canada(
        override val value: String = "Canada",
        override val flag: String = "🇨🇦",
        override val code: String = "CA",
    ) : Country()

    data class CapeVerde(
        override val value: String = "Cap-Vert",
        override val flag: String = "🇨🇻",
        override val code: String = "CV",
    ) : Country()

    data class CaymanIslands(
        override val value: String = "Îles Caïmans",
        override val flag: String = "🇰🇾",
        override val code: String = "KY",
    ) : Country()

    data class CentralAfricanRepublic(
        override val value: String = "République centrafricaine",
        override val flag: String = "🇨🇫",
        override val code: String = "CF",
    ) : Country()

    data class Chad(
        override val value: String = "Tchad",
        override val flag: String = "🇹🇩",
        override val code: String = "TD",
    ) : Country()

    data class Chile(
        override val value: String = "Chili",
        override val flag: String = "🇨🇱",
        override val code: String = "CL",
    ) : Country()

    data class China(
        override val value: String = "Chine",
        override val flag: String = "🇨🇳",
        override val code: String = "CN",
    ) : Country()

    data class ChristmasIsland(
        override val value: String = "Île Christmas",
        override val flag: String = "🇨🇽",
        override val code: String = "CX",
    ) : Country()

    data class CocosIslands(
        override val value: String = "Îles Cocos",
        override val flag: String = "🇨🇨",
        override val code: String = "CC",
    ) : Country()

    data class Comoros(
        override val value: String = "Comores",
        override val flag: String = "🇰🇲",
        override val code: String = "KM",
    ) : Country()

    data class CongoBrazzaville(
        override val value: String = "Congo-Brazzaville",
        override val flag: String = "🇨🇬",
        override val code: String = "CG",
    ) : Country()

    data class CongoKinshasa(
        override val value: String = "Congo-Kinshasa",
        override val flag: String = "🇨🇩",
        override val code: String = "CD",
    ) : Country()

    data class CookIslands(
        override val value: String = "Îles Cook",
        override val flag: String = "🇨🇰",
        override val code: String = "CK",
    ) : Country()

    data class CostaRica(
        override val value: String = "Costa Rica",
        override val flag: String = "🇨🇷",
        override val code: String = "CR",
    ) : Country()

    data class Curacao(
        override val value: String = "Curaçao",
        override val flag: String = "🇨🇼",
        override val code: String = "CW",
    ) : Country()

    data class Cyprus(
        override val value: String = "Chypre",
        override val flag: String = "🇨🇾",
        override val code: String = "CY",
    ) : Country()

    data class Czechia(
        override val value: String = "Tchéquie",
        override val flag: String = "🇨🇿",
        override val code: String = "CZ",
    ) : Country()

    data class Denmark(
        override val value: String = "Danemark",
        override val flag: String = "🇩🇰",
        override val code: String = "DK",
    ) : Country()

    data class Djibouti(
        override val value: String = "Djibouti",
        override val flag: String = "🇩🇯",
        override val code: String = "DJ",
    ) : Country()

    data class Dominica(
        override val value: String = "Dominique",
        override val flag: String = "🇩🇲",
        override val code: String = "DM",
    ) : Country()

    data class DominicanRepublic(
        override val value: String = "République dominicaine",
        override val flag: String = "🇩🇴",
        override val code: String = "DO",
    ) : Country()

    data class EastTimor(
        override val value: String = "Timor oriental",
        override val flag: String = "🇹🇱",
        override val code: String = "TL",
    ) : Country()

    data class Ecuador(
        override val value: String = "Équateur",
        override val flag: String = "🇪🇨",
        override val code: String = "EC",
    ) : Country()

    data class Egypt(
        override val value: String = "Égypte",
        override val flag: String = "🇪🇬",
        override val code: String = "EG",
    ) : Country()

    data class ElSalvador(
        override val value: String = "Salvador",
        override val flag: String = "🇸🇻",
        override val code: String = "SV",
    ) : Country()

    data class Eritrea(
        override val value: String = "Érythrée",
        override val flag: String = "🇪🇷",
        override val code: String = "ER",
    ) : Country()

    data class Estonia(
        override val value: String = "Estonie",
        override val flag: String = "🇪🇪",
        override val code: String = "EE",
    ) : Country()

    data class Eswatini(
        override val value: String = "Eswatini",
        override val flag: String = "🇸🇿",
        override val code: String = "SZ",
    ) : Country()

    data class Ethiopia(
        override val value: String = "Éthiopie",
        override val flag: String = "🇪🇹",
        override val code: String = "ET",
    ) : Country()

    data class FalklandIslands(
        override val value: String = "Îles Malouines",
        override val flag: String = "🇫🇰",
        override val code: String = "FK",
    ) : Country()

    data class FaroeIslands(
        override val value: String = "Îles Féroé",
        override val flag: String = "🇫🇴",
        override val code: String = "FO",
    ) : Country()

    data class Fiji(
        override val value: String = "Fidji",
        override val flag: String = "🇫🇯",
        override val code: String = "FJ",
    ) : Country()

    data class Finland(
        override val value: String = "Finlande",
        override val flag: String = "🇫🇮",
        override val code: String = "FI",
    ) : Country()

    data class France(
        override val value: String = "France",
        override val flag: String = "🇫🇷",
        override val code: String = "FR",
    ) : Country()

    data class FrenchGuiana(
        override val value: String = "Guyane française",
        override val flag: String = "🇬🇫",
        override val code: String = "GF",
    ) : Country()

    data class FrenchPolynesia(
        override val value: String = "Polynésie française",
        override val flag: String = "🇵🇫",
        override val code: String = "PF",
    ) : Country()

    data class FrenchSouthernTerritories(
        override val value: String = "Terres australes françaises",
        override val flag: String = "🇹🇫",
        override val code: String = "TF",
    ) : Country()

    data class Gabon(
        override val value: String = "Gabon",
        override val flag: String = "🇬🇦",
        override val code: String = "GA",
    ) : Country()

    data class Gambia(
        override val value: String = "Gambie",
        override val flag: String = "🇬🇲",
        override val code: String = "GM",
    ) : Country()

    data class Georgia(
        override val value: String = "Géorgie",
        override val flag: String = "🇬🇪",
        override val code: String = "GE",
    ) : Country()

    data class Germany(
        override val value: String = "Allemagne",
        override val flag: String = "🇩🇪",
        override val code: String = "DE",
    ) : Country()

    data class Ghana(
        override val value: String = "Ghana",
        override val flag: String = "🇬🇭",
        override val code: String = "GH",
    ) : Country()

    data class Gibraltar(
        override val value: String = "Gibraltar",
        override val flag: String = "🇬🇮",
        override val code: String = "GI",
    ) : Country()

    data class Greece(
        override val value: String = "Grèce",
        override val flag: String = "🇬🇷",
        override val code: String = "GR",
    ) : Country()

    data class Greenland(
        override val value: String = "Groenland",
        override val flag: String = "🇬🇱",
        override val code: String = "GL",
    ) : Country()

    data class Grenada(
        override val value: String = "Grenade",
        override val flag: String = "🇬🇩",
        override val code: String = "GD",
    ) : Country()

    data class Guadeloupe(
        override val value: String = "Guadeloupe",
        override val flag: String = "🇬🇵",
        override val code: String = "GP",
    ) : Country()

    data class Guam(
        override val value: String = "Guam",
        override val flag: String = "🇬🇺",
        override val code: String = "GU",
    ) : Country()

    data class Guatemala(
        override val value: String = "Guatemala",
        override val flag: String = "🇬🇹",
        override val code: String = "GT",
    ) : Country()

    data class Guernsey(
        override val value: String = "Guernesey",
        override val flag: String = "🇬🇬",
        override val code: String = "GG",
    ) : Country()

    data class Guinea(
        override val value: String = "Guinée",
        override val flag: String = "🇬🇳",
        override val code: String = "GN",
    ) : Country()

    data class EquatorialGuinea(
        override val value: String = "Guinée équatoriale",
        override val flag: String = "🇬🇶",
        override val code: String = "GQ",
    ) : Country()

    data class GuineaBissau(
        override val value: String = "Guinée-Bissau",
        override val flag: String = "🇬🇼",
        override val code: String = "GW",
    ) : Country()

    data class Guyana(
        override val value: String = "Guyana",
        override val flag: String = "🇬🇾",
        override val code: String = "GY",
    ) : Country()

    data class Haiti(
        override val value: String = "Haïti",
        override val flag: String = "🇭🇹",
        override val code: String = "HT",
    ) : Country()

    data class Honduras(
        override val value: String = "Honduras",
        override val flag: String = "🇭🇳",
        override val code: String = "HN",
    ) : Country()

    data class HongKongSAR(
        override val value: String = "R.A.S. chinoise de Hong Kong",
        override val flag: String = "🇭🇰",
        override val code: String = "HK",
    ) : Country()

    data class HeardAndMcDonaldIslands(
        override val value: String = "Îles Heard-et-MacDonald",
        override val flag: String = "🇭🇲",
        override val code: String = "HM",
    ) : Country()

    data class Hungary(
        override val value: String = "Hongrie",
        override val flag: String = "🇭🇺",
        override val code: String = "HU",
    ) : Country()

    data class Iceland(
        override val value: String = "Islande",
        override val flag: String = "🇮🇸",
        override val code: String = "IS",
    ) : Country()

    data class India(
        override val value: String = "Inde",
        override val flag: String = "🇮🇳",
        override val code: String = "IN",
    ) : Country()

    data class Indonesia(
        override val value: String = "Indonésie",
        override val flag: String = "🇮🇩",
        override val code: String = "ID",
    ) : Country()

    data class Iran(
        override val value: String = "Iran",
        override val flag: String = "🇮🇷",
        override val code: String = "IR",
    ) : Country()

    data class Iraq(
        override val value: String = "Irak",
        override val flag: String = "🇮🇶",
        override val code: String = "IQ",
    ) : Country()

    data class Ireland(
        override val value: String = "Irlande",
        override val flag: String = "🇮🇪",
        override val code: String = "IE",
    ) : Country()

    data class IsleOfMan(
        override val value: String = "Île de Man",
        override val flag: String = "🇮🇲",
        override val code: String = "IM",
    ) : Country()

    data class Israel(
        override val value: String = "Israël",
        override val flag: String = "🇮🇱",
        override val code: String = "IL",
    ) : Country()

    data class Italy(
        override val value: String = "Italie",
        override val flag: String = "🇮🇹",
        override val code: String = "IT",
    ) : Country()

    data class Jamaica(
        override val value: String = "Jamaïque",
        override val flag: String = "🇯🇲",
        override val code: String = "JM",
    ) : Country()

    data class Japan(
        override val value: String = "Japon",
        override val flag: String = "🇯🇵",
        override val code: String = "JP",
    ) : Country()

    data class Jersey(
        override val value: String = "Jersey",
        override val flag: String = "🇯🇪",
        override val code: String = "JE",
    ) : Country()

    data class Jordan(
        override val value: String = "Jordanie",
        override val flag: String = "🇯🇴",
        override val code: String = "JO",
    ) : Country()

    data class Kazakhstan(
        override val value: String = "Kazakhstan",
        override val flag: String = "🇰🇿",
        override val code: String = "KZ",
    ) : Country()

    data class Kenya(
        override val value: String = "Kenya",
        override val flag: String = "🇰🇪",
        override val code: String = "KE",
    ) : Country()

    data class Kiribati(
        override val value: String = "Kiribati",
        override val flag: String = "🇰🇮",
        override val code: String = "KI",
    ) : Country()

    data class Kosovo(
        override val value: String = "Kosovo",
        override val flag: String = "🇽🇰",
        override val code: String = "XK",
    ) : Country()

    data class Kuwait(
        override val value: String = "Koweït",
        override val flag: String = "🇰🇼",
        override val code: String = "KW",
    ) : Country()

    data class Kyrgyzstan(
        override val value: String = "Kirghizstan",
        override val flag: String = "🇰🇬",
        override val code: String = "KG",
    ) : Country()

    data class Laos(
        override val value: String = "Laos",
        override val flag: String = "🇱🇦",
        override val code: String = "LA",
    ) : Country()

    data class Latvia(
        override val value: String = "Lettonie",
        override val flag: String = "🇱🇻",
        override val code: String = "LV",
    ) : Country()

    data class Lebanon(
        override val value: String = "Liban",
        override val flag: String = "🇱🇧",
        override val code: String = "LB",
    ) : Country()

    data class Lesotho(
        override val value: String = "Lesotho",
        override val flag: String = "🇱🇸",
        override val code: String = "LS",
    ) : Country()

    data class Liberia(
        override val value: String = "Liberia",
        override val flag: String = "🇱🇷",
        override val code: String = "LR",
    ) : Country()

    data class Libya(
        override val value: String = "Libye",
        override val flag: String = "🇱🇾",
        override val code: String = "LY",
    ) : Country()

    data class Liechtenstein(
        override val value: String = "Liechtenstein",
        override val flag: String = "🇱🇮",
        override val code: String = "LI",
    ) : Country()

    data class Lithuania(
        override val value: String = "Lituanie",
        override val flag: String = "🇱🇹",
        override val code: String = "LT",
    ) : Country()

    data class Luxembourg(
        override val value: String = "Luxembourg",
        override val flag: String = "🇱🇺",
        override val code: String = "LU",
    ) : Country()

    data class MacaoSAR(
        override val value: String = "R.A.S. chinoise de Macao",
        override val flag: String = "🇲🇴",
        override val code: String = "MO",
    ) : Country()

    data class Macedonia(
        override val value: String = "Macédoine du Nord",
        override val flag: String = "🇲🇰",
        override val code: String = "MK",
    ) : Country()

    data class Madagascar(
        override val value: String = "Madagascar",
        override val flag: String = "🇲🇬",
        override val code: String = "MG",
    ) : Country()

    data class Malawi(
        override val value: String = "Malawi",
        override val flag: String = "🇲🇼",
        override val code: String = "MW",
    ) : Country()

    data class Malaysia(
        override val value: String = "Malaisie",
        override val flag: String = "🇲🇾",
        override val code: String = "MY",
    ) : Country()

    data class Maldives(
        override val value: String = "Maldives",
        override val flag: String = "🇲🇻",
        override val code: String = "MV",
    ) : Country()

    data class Mali(
        override val value: String = "Mali",
        override val flag: String = "🇲🇱",
        override val code: String = "ML",
    ) : Country()

    data class Malta(
        override val value: String = "Malte",
        override val flag: String = "🇲🇹",
        override val code: String = "MT",
    ) : Country()

    data class MarshallIslands(
        override val value: String = "Îles Marshall",
        override val flag: String = "🇲🇭",
        override val code: String = "MH",
    ) : Country()

    data class Martinique(
        override val value: String = "Martinique",
        override val flag: String = "🇲🇶",
        override val code: String = "MQ",
    ) : Country()

    data class Mauritania(
        override val value: String = "Mauritanie",
        override val flag: String = "🇲🇷",
        override val code: String = "MR",
    ) : Country()

    data class Mauritius(
        override val value: String = "Maurice",
        override val flag: String = "🇲🇺",
        override val code: String = "MU",
    ) : Country()

    data class Mayotte(
        override val value: String = "Mayotte",
        override val flag: String = "🇾🇹",
        override val code: String = "YT",
    ) : Country()

    data class Mexico(
        override val value: String = "Mexique",
        override val flag: String = "🇲🇽",
        override val code: String = "MX",
    ) : Country()

    data class Micronesia(
        override val value: String = "Micronésie",
        override val flag: String = "🇫🇲",
        override val code: String = "FM",
    ) : Country()

    data class Moldova(
        override val value: String = "Moldavie",
        override val flag: String = "🇲🇩",
        override val code: String = "MD",
    ) : Country()

    data class Monaco(
        override val value: String = "Monaco",
        override val flag: String = "🇲🇨",
        override val code: String = "MC",
    ) : Country()

    data class Mongolia(
        override val value: String = "Mongolie",
        override val flag: String = "🇲🇳",
        override val code: String = "MN",
    ) : Country()

    data class Montenegro(
        override val value: String = "Monténégro",
        override val flag: String = "🇲🇪",
        override val code: String = "ME",
    ) : Country()

    data class Montserrat(
        override val value: String = "Montserrat",
        override val flag: String = "🇲🇸",
        override val code: String = "MS",
    ) : Country()

    data class Morocco(
        override val value: String = "Maroc",
        override val flag: String = "🇲🇦",
        override val code: String = "MA",
    ) : Country()

    data class Mozambique(
        override val value: String = "Mozambique",
        override val flag: String = "🇲🇿",
        override val code: String = "MZ",
    ) : Country()

    data class Myanmar(
        override val value: String = "Myanmar (Birmanie)",
        override val flag: String = "🇲🇲",
        override val code: String = "MM",
    ) : Country()

    data class Namibia(
        override val value: String = "Namibie",
        override val flag: String = "🇳🇦",
        override val code: String = "NA",
    ) : Country()

    data class Nauru(
        override val value: String = "Nauru",
        override val flag: String = "🇳🇷",
        override val code: String = "NR",
    ) : Country()

    data class Nepal(
        override val value: String = "Népal",
        override val flag: String = "🇳🇵",
        override val code: String = "NP",
    ) : Country()

    data class Netherlands(
        override val value: String = "Pays-Bas",
        override val flag: String = "🇳🇱",
        override val code: String = "NL",
    ) : Country()

    data class NetherlandsAntilles(
        override val value: String = "Pays-Bas caribéens",
        override val flag: String = "🇧🇶",
        override val code: String = "AN",
    ) : Country()

    data class NewCaledonia(
        override val value: String = "Nouvelle-Calédonie",
        override val flag: String = "🇳🇨",
        override val code: String = "NC",
    ) : Country()

    data class NewZealand(
        override val value: String = "Nouvelle-Zélande",
        override val flag: String = "🇳🇿",
        override val code: String = "NZ",
    ) : Country()

    data class Nicaragua(
        override val value: String = "Nicaragua",
        override val flag: String = "🇳🇮",
        override val code: String = "NI",
    ) : Country()

    data class Niger(
        override val value: String = "Niger",
        override val flag: String = "🇳🇪",
        override val code: String = "NE",
    ) : Country()

    data class Nigeria(
        override val value: String = "Nigeria",
        override val flag: String = "🇳🇬",
        override val code: String = "NG",
    ) : Country()

    data class Niue(
        override val value: String = "Niue",
        override val flag: String = "🇳🇺",
        override val code: String = "NU",
    ) : Country()

    data class NorfolkIsland(
        override val value: String = "Île Norfolk",
        override val flag: String = "🇳🇫",
        override val code: String = "NF",
    ) : Country()

    data class NorthKorea(
        override val value: String = "Corée du Nord",
        override val flag: String = "🇰🇵",
        override val code: String = "KP",
    ) : Country()

    data class NorthernMarianaIslands(
        override val value: String = "Îles Mariannes du Nord",
        override val flag: String = "🇲🇵",
        override val code: String = "MP",
    ) : Country()

    data class Norway(
        override val value: String = "Norvège",
        override val flag: String = "🇳🇴",
        override val code: String = "NO",
    ) : Country()

    data class Oman(
        override val value: String = "Oman",
        override val flag: String = "🇴🇲",
        override val code: String = "OM",
    ) : Country()

    data class Pakistan(
        override val value: String = "Pakistan",
        override val flag: String = "🇵🇰",
        override val code: String = "PK",
    ) : Country()

    data class Palau(
        override val value: String = "Palau",
        override val flag: String = "🇵🇼",
        override val code: String = "PW",
    ) : Country()

    data class PalestinianTerritories(
        override val value: String = "Palestine",
        override val flag: String = "🇵🇸",
        override val code: String = "PS",
    ) : Country()

    data class Panama(
        override val value: String = "Panama",
        override val flag: String = "🇵🇳",
        override val code: String = "PA",
    ) : Country()

    data class PapuaNewGuinea(
        override val value: String = "Papouasie-Nouvelle-Guinée",
        override val flag: String = "🇵🇬",
        override val code: String = "PG",
    ) : Country()

    data class Paraguay(
        override val value: String = "Paraguay",
        override val flag: String = "🇵🇾",
        override val code: String = "PY",
    ) : Country()

    data class Peru(
        override val value: String = "Pérou",
        override val flag: String = "🇵🇪",
        override val code: String = "PE",
    ) : Country()

    data class Philippines(
        override val value: String = "Philippines",
        override val flag: String = "🇵🇭",
        override val code: String = "PH",
    ) : Country()

    data class PitcairnIslands(
        override val value: String = "Îles Pitcairn",
        override val flag: String = "🇵🇳",
        override val code: String = "PN",
    ) : Country()

    data class Poland(
        override val value: String = "Pologne",
        override val flag: String = "🇵🇱",
        override val code: String = "PL",
    ) : Country()

    data class Portugal(
        override val value: String = "Portugal",
        override val flag: String = "🇵🇹",
        override val code: String = "PT",
    ) : Country()

    data class PuertoRico(
        override val value: String = "Porto Rico",
        override val flag: String = "🇵🇷",
        override val code: String = "PR",
    ) : Country()

    data class Qatar(
        override val value: String = "Qatar",
        override val flag: String = "🇶🇦",
        override val code: String = "QA",
    ) : Country()

    data class Reunion(
        override val value: String = "La Réunion",
        override val flag: String = "🇷🇪",
        override val code: String = "RE",
    ) : Country()

    data class Romania(
        override val value: String = "Roumanie",
        override val flag: String = "🇷🇴",
        override val code: String = "RO",
    ) : Country()

    data class Russia(
        override val value: String = "Russie",
        override val flag: String = "🇷🇺",
        override val code: String = "RU",
    ) : Country()

    data class Rwanda(
        override val value: String = "Rwanda",
        override val flag: String = "🇷🇼",
        override val code: String = "RW",
    ) : Country()

    data class SaintBarthelemy(
        override val value: String = "Saint-Barthélemy",
        override val flag: String = "🇧🇱",
        override val code: String = "BL",
    ) : Country()

    data class SaintHelena(
        override val value: String = "Sainte-Hélène",
        override val flag: String = "🇸🇭",
        override val code: String = "SH",
    ) : Country()

    data class SaintKittsAndNevis(
        override val value: String = "Saint-Christophe-et-Niévès",
        override val flag: String = "🇰🇳",
        override val code: String = "KN",
    ) : Country()

    data class SaintLucia(
        override val value: String = "Sainte-Lucie",
        override val flag: String = "🇱🇨",
        override val code: String = "LC",
    ) : Country()

    data class SaintPierreAndMiquelon(
        override val value: String = "Saint-Pierre-et-Miquelon",
        override val flag: String = "🇵🇲",
        override val code: String = "PM",
    ) : Country()

    data class SaintVincentAndTheGrenadines(
        override val value: String = "Saint-Vincent-et-les Grenadines",
        override val flag: String = "🇻🇨",
        override val code: String = "VC",
    ) : Country()

    data class Samoa(
        override val value: String = "Samoa",
        override val flag: String = "🇼🇸",
        override val code: String = "WS",
    ) : Country()

    data class SanMarino(
        override val value: String = "Saint-Marin",
        override val flag: String = "🇸🇲",
        override val code: String = "SM",
    ) : Country()

    data class SaoTomeAndPrincipe(
        override val value: String = "Sao Tomé-et-Principe",
        override val flag: String = "🇸🇹",
        override val code: String = "ST",
    ) : Country()

    data class SaudiArabia(
        override val value: String = "Arabie saoudite",
        override val flag: String = "🇸🇦",
        override val code: String = "SA",
    ) : Country()

    data class Senegal(
        override val value: String = "Sénégal",
        override val flag: String = "🇸🇳",
        override val code: String = "SN",
    ) : Country()

    data class Serbia(
        override val value: String = "Serbie",
        override val flag: String = "🇷🇸",
        override val code: String = "RS",
    ) : Country()

    data class Seychelles(
        override val value: String = "Seychelles",
        override val flag: String = "🇸🇨",
        override val code: String = "SC",
    ) : Country()

    data class SierraLeone(
        override val value: String = "Sierra Leone",
        override val flag: String = "🇸🇱",
        override val code: String = "SL",
    ) : Country()

    data class Singapore(
        override val value: String = "Singapour",
        override val flag: String = "🇸🇬",
        override val code: String = "SG",
    ) : Country()

    data class Slovakia(
        override val value: String = "Slovaquie",
        override val flag: String = "🇸🇰",
        override val code: String = "SK",
    ) : Country()

    data class Slovenia(
        override val value: String = "Slovénie",
        override val flag: String = "🇸🇮",
        override val code: String = "SI",
    ) : Country()

    data class SolomonIslands(
        override val value: String = "Îles Salomon",
        override val flag: String = "🇸🇧",
        override val code: String = "SB",
    ) : Country()

    data class Somalia(
        override val value: String = "Somalie",
        override val flag: String = "🇸🇴",
        override val code: String = "SO",
    ) : Country()

    data class Somaliland(
        override val value: String = "Somaliland",
        override val flag: String = "🇸🇴",
        override val code: String = "SOL",
    ) : Country()

    data class SouthAfrica(
        override val value: String = "Afrique du Sud",
        override val flag: String = "🇿🇦",
        override val code: String = "ZA",
    ) : Country()

    data class SouthKorea(
        override val value: String = "Corée du Sud",
        override val flag: String = "🇰🇷",
        override val code: String = "KR",
    ) : Country()

    data class SouthSudan(
        override val value: String = "Soudan du Sud",
        override val flag: String = "🇸🇸",
        override val code: String = "SS",
    ) : Country()

    data class Spain(
        override val value: String = "Espagne",
        override val flag: String = "🇪🇸",
        override val code: String = "ES",
    ) : Country()

    data class SriLanka(
        override val value: String = "Sri Lanka",
        override val flag: String = "🇱🇰",
        override val code: String = "LK",
    ) : Country()

    data class Sudan(
        override val value: String = "Soudan",
        override val flag: String = "🇸🇩",
        override val code: String = "SD",
    ) : Country()

    data class Suriname(
        override val value: String = "Suriname",
        override val flag: String = "🇸🇷",
        override val code: String = "SR",
    ) : Country()

    data class SvalbardAndJanMayen(
        override val value: String = "Svalbard et Jan Mayen",
        override val flag: String = "🇸🇯",
        override val code: String = "SJ",
    ) : Country()

    data class Swaziland(
        override val value: String = "Swaziland",
        override val flag: String = "🇸🇿",
        override val code: String = "SWZ",
    ) : Country()

    data class Sweden(
        override val value: String = "Suède",
        override val flag: String = "🇸🇪",
        override val code: String = "SE",
    ) : Country()

    data class Switzerland(
        override val value: String = "Suisse",
        override val flag: String = "🇨🇭",
        override val code: String = "CH",
    ) : Country()

    data class Syria(
        override val value: String = "Syrie",
        override val flag: String = "🇸🇾",
        override val code: String = "SY",
    ) : Country()

    data class Taiwan(
        override val value: String = "Taïwan",
        override val flag: String = "🇹🇼",
        override val code: String = "TW",
    ) : Country()

    data class Tajikistan(
        override val value: String = "Tadjikistan",
        override val flag: String = "🇹🇯",
        override val code: String = "TJ",
    ) : Country()

    data class Tanzania(
        override val value: String = "Tanzanie",
        override val flag: String = "🇹🇿",
        override val code: String = "TZ",
    ) : Country()

    data class Thailand(
        override val value: String = "Thaïlande",
        override val flag: String = "🇹🇭",
        override val code: String = "TH",
    ) : Country()

    data class Togo(
        override val value: String = "Togo",
        override val flag: String = "🇹🇬",
        override val code: String = "TG",
    ) : Country()

    data class Tokelau(
        override val value: String = "Tokelau",
        override val flag: String = "🇹🇰",
        override val code: String = "TK",
    ) : Country()

    data class Tonga(
        override val value: String = "Tonga",
        override val flag: String = "🇹🇴",
        override val code: String = "TO",
    ) : Country()

    data class Transnistrie(
        override val value: String = "Transnistrie",
        override val flag: String = "🇲🇩",
        override val code: String = "PMR",
    ) : Country()

    data class TrinidadAndTobago(
        override val value: String = "Trinité-et-Tobago",
        override val flag: String = "🇹🇹",
        override val code: String = "TT",
    ) : Country()

    data class Tunisia(
        override val value: String = "Tunisie",
        override val flag: String = "🇹🇳",
        override val code: String = "TN",
    ) : Country()

    data class Turkey(
        override val value: String = "Turquie",
        override val flag: String = "🇹🇷",
        override val code: String = "TR",
    ) : Country()

    data class Turkmenistan(
        override val value: String = "Turkménistan",
        override val flag: String = "🇹🇲",
        override val code: String = "TM",
    ) : Country()

    data class TurksAndCaicosIslands(
        override val value: String = "Îles Turques-et-Caïques",
        override val flag: String = "🇹🇨",
        override val code: String = "TC",
    ) : Country()

    data class Tuvalu(
        override val value: String = "Tuvalu",
        override val flag: String = "🇹🇻",
        override val code: String = "TV",
    ) : Country()

    data class Uganda(
        override val value: String = "Ouganda",
        override val flag: String = "🇺🇬",
        override val code: String = "UG",
    ) : Country()

    data class Ukraine(
        override val value: String = "Ukraine",
        override val flag: String = "🇺🇦",
        override val code: String = "UA",
    ) : Country()

    data class UnitedArabEmirates(
        override val value: String = "Émirats arabes unis",
        override val flag: String = "🇦🇪",
        override val code: String = "AE",
    ) : Country()

    data class UnitedKingdom(
        override val value: String = "Royaume-Uni",
        override val flag: String = "🇬🇧",
        override val code: String = "GB",
    ) : Country()

    data class UnitedStates(
        override val value: String = "États-Unis",
        override val flag: String = "🇺🇸",
        override val code: String = "US",
    ) : Country()

    data class USOutlyingIslands(
        override val value: String = "Îles mineures éloignées des États-Unis",
        override val flag: String = "🇺🇲",
        override val code: String = "UM",
    ) : Country()

    data class USVirginIslands(
        override val value: String = "Îles Vierges des États-Unis",
        override val flag: String = "🇻🇮",
        override val code: String = "VI",
    ) : Country()

    data class Uruguay(
        override val value: String = "Uruguay",
        override val flag: String = "🇺🇾",
        override val code: String = "UY",
    ) : Country()

    data class Uzbekistan(
        override val value: String = "Ouzbékistan",
        override val flag: String = "🇺🇿",
        override val code: String = "UZ",
    ) : Country()

    data class Vanuatu(
        override val value: String = "Vanuatu",
        override val flag: String = "🇻🇺",
        override val code: String = "VU",
    ) : Country()

    data class VaticanCityState(
        override val value: String = "État de la Cité du Vatican",
        override val flag: String = "🇻🇦",
        override val code: String = "VA",
    ) : Country()

    data class Venezuela(
        override val value: String = "Venezuela",
        override val flag: String = "🇻🇪",
        override val code: String = "VE",
    ) : Country()

    data class Vietnam(
        override val value: String = "Viêt Nam",
        override val flag: String = "🇻🇳",
        override val code: String = "VN",
    ) : Country()

    data class WallisAndFutuna(
        override val value: String = "Wallis-et-Futuna",
        override val flag: String = "🇼🇫",
        override val code: String = "WF",
    ) : Country()

    data class WesternSahara(
        override val value: String = "Sahara occidental",
        override val flag: String = "🇪🇭",
        override val code: String = "EH",
    ) : Country()

    data class Yemen(
        override val value: String = "Yémen",
        override val flag: String = "🇾🇪",
        override val code: String = "YE",
    ) : Country()

    data class Zambia(
        override val value: String = "Zambie",
        override val flag: String = "🇿🇲",
        override val code: String = "ZM",
    ) : Country()

    data class Zimbabwe(
        override val value: String = "Zimbabwe",
        override val flag: String = "🇿🇼",
        override val code: String = "ZW",
    ) : Country()

    companion object {
        fun toList() = countryList

        fun fromCode(code: String): Country? {
            return toList().firstOrNull { it.code == code }
        }
    }
}
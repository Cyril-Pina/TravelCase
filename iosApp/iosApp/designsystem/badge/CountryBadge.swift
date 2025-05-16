import SwiftUI
import Shared

struct CountryBadge: View {
    let intent: CountryIntent
    
    var body: some View {
        HStack(spacing: 4) {
            Text(text: intent.value.flag, intent: .small)
            Text(text: intent.value.value, intent: .small)
        }
        .padding(.horizontal, 8)
        .padding(.vertical, 4)
        .background(Color.white)
        .clipShape(Capsule())
    }
}

enum CountryIntent: CaseIterable {
    case afghanistan
    case alandIslands
    case albania
    case algeria
    case americanSamoa
    case andorra
    case angola
    case anguilla
    case antarctica
    case antiguaAndBarbuda
    case argentina
    case armenia
    case aruba
    case australia
    case austria
    case azerbaijan
    case bahamas
    case bahrain
    case bangladesh
    case barbados
    case belarus
    case belgium
    case belize
    case benin
    case bermuda
    case bhutan
    case bolivia
    case bonaireSaintEustacheAndSaba
    case bosniaAndHerzegovina
    case botswana
    case bouvetIsland
    case brazil
    case britishIndianOceanTerritory
    case britishVirginIslands
    case bulgaria
    case burkinaFaso
    case burundi
    case cambodia
    case cameroon
    case canada
    case capeVerde
    case caymanIslands
    case centralAfricanRepublic
    case chad
    case chile
    case china
    case christmasIsland
    case cocosIslands
    case comoros
    case congoBrazzaville
    case congoKinshasa
    case cookIslands
    case costaRica
    case curacao
    case cyprus
    case czechia
    case denmark
    case djibouti
    case dominica
    case dominicanRepublic
    case eastTimor
    case ecuador
    case egypt
    case elSalvador
    case equatorialGuinea
    case eritrea
    case estonia
    case eswatini
    case ethiopia
    case falklandIslands
    case faroeIslands
    case fiji
    case finland
    case france
    case frenchGuiana
    case frenchPolynesia
    case frenchSouthernTerritories
    case gabon
    case gambia
    case georgia
    case germany
    case ghana
    case gibraltar
    case greece
    case greenland
    case grenada
    case guadeloupe
    case guam
    case guatemala
    case guernsey
    case guinea
    case guineaBissau
    case guyana
    case haiti
    case heardAndMcDonaldIslands
    case honduras
    case hongKongSAR
    case hungary
    case iceland
    case india
    case indonesia
    case iran
    case iraq
    case ireland
    case isleOfMan
    case israel
    case italy
    case jamaica
    case japan
    case jersey
    case jordan
    case kazakhstan
    case kenya
    case kiribati
    case kosovo
    case kuwait
    case kyrgyzstan
    case laos
    case latvia
    case lebanon
    case lesotho
    case liberia
    case libya
    case liechtenstein
    case lithuania
    case luxembourg
    case macaoSAR
    case macedonia
    case madagascar
    case malawi
    case malaysia
    case maldives
    case mali
    case malta
    case marshallIslands
    case martinique
    case mauritania
    case mauritius
    case mayotte
    case mexico
    case micronesia
    case moldova
    case monaco
    case mongolia
    case montenegro
    case montserrat
    case morocco
    case mozambique
    case myanmar
    case namibia
    case nauru
    case nepal
    case netherlands
    case netherlandsAntilles
    case newCaledonia
    case newZealand
    case nicaragua
    case niger
    case nigeria
    case niue
    case norfolkIsland
    case northKorea
    case northernMarianaIslands
    case norway
    case oman
    case pakistan
    case palau
    case palestinianTerritories
    case panama
    case papuaNewGuinea
    case paraguay
    case peru
    case philippines
    case pitcairnIslands
    case poland
    case portugal
    case puertoRico
    case qatar
    case reunion
    case romania
    case russia
    case rwanda
    case saintBarthelemy
    case saintHelena
    case saintKittsAndNevis
    case saintLucia
    case saintPierreAndMiquelon
    case saintVincentAndTheGrenadines
    case samoa
    case sanMarino
    case saoTomeAndPrincipe
    case saudiArabia
    case senegal
    case serbia
    case seychelles
    case sierraLeone
    case singapore
    case slovakia
    case slovenia
    case solomonIslands
    case somalia
    case somaliland
    case southAfrica
    case southKorea
    case southSudan
    case spain
    case sriLanka
    case sudan
    case suriname
    case svalbardAndJanMayen
    case swaziland
    case sweden
    case switzerland
    case syria
    case taiwan
    case tajikistan
    case tanzania
    case thailand
    case togo
    case tokelau
    case tonga
    case transnistrie
    case trinidadAndTobago
    case tunisia
    case turkey
    case turkmenistan
    case turksAndCaicosIslands
    case tuvalu
    case uganda
    case ukraine
    case unitedArabEmirates
    case unitedKingdom
    case unitedStates
    case usOutlyingIslands
    case usVirginIslands
    case uruguay
    case uzbekistan
    case vanuatu
    case vaticanCityState
    case venezuela
    case vietnam
    case wallisAndFutuna
    case westernSahara
    case yemen
    case zambia
    case zimbabwe
    
    var value: CountryIntentValue {
        switch self {
        case .afghanistan:
            return CountryIntentValue(value: "Afghanistan", flag: "🇦🇫", code: "AF")
        case .alandIslands:
            return CountryIntentValue(value: "Îles Åland", flag: "🇦🇽", code: "AX")
        case .albania:
            return CountryIntentValue(value: "Albanie", flag: "🇦🇱", code: "AL")
        case .algeria:
            return CountryIntentValue(value: "Algérie", flag: "🇩🇿", code: "DZ")
        case .americanSamoa:
            return CountryIntentValue(value: "Samoa américaines", flag: "🇦🇸", code: "AS")
        case .andorra:
            return CountryIntentValue(value: "Andorre", flag: "🇦🇩", code: "AD")
        case .angola:
            return CountryIntentValue(value: "Angola", flag: "🇦🇴", code: "AO")
        case .anguilla:
            return CountryIntentValue(value: "Anguilla", flag: "🇦🇮", code: "AI")
        case .antarctica:
            return CountryIntentValue(value: "Antarctique", flag: "🇦🇶", code: "AQ")
        case .antiguaAndBarbuda:
            return CountryIntentValue(value: "Antigua-et-Barbuda", flag: "🇦🇬", code: "AG")
        case .argentina:
            return CountryIntentValue(value: "Argentine", flag: "🇦🇷", code: "AR")
        case .armenia:
            return CountryIntentValue(value: "Arménie", flag: "🇦🇲", code: "AM")
        case .aruba:
            return CountryIntentValue(value: "Aruba", flag: "🇦🇼", code: "AW")
        case .australia:
            return CountryIntentValue(value: "Australie", flag: "🇦🇺", code: "AU")
        case .austria:
            return CountryIntentValue(value: "Autriche", flag: "🇦🇹", code: "AT")
        case .azerbaijan:
            return CountryIntentValue(value: "Azerbaïdjan", flag: "🇦🇿", code: "AZ")
        case .bahamas:
            return CountryIntentValue(value: "Bahamas", flag: "🇧🇸", code: "BS")
        case .bahrain:
            return CountryIntentValue(value: "Bahreïn", flag: "🇧🇭", code: "BH")
        case .bangladesh:
            return CountryIntentValue(value: "Bangladesh", flag: "🇧🇩", code: "BD")
        case .barbados:
            return CountryIntentValue(value: "Barbade", flag: "🇧🇧", code: "BB")
        case .belarus:
            return CountryIntentValue(value: "Biélorussie", flag: "🇧🇾", code: "BY")
        case .belgium:
            return CountryIntentValue(value: "Belgique", flag: "🇧🇪", code: "BE")
        case .belize:
            return CountryIntentValue(value: "Belize", flag: "🇧🇿", code: "BZ")
        case .benin:
            return CountryIntentValue(value: "Bénin", flag: "🇧🇯", code: "BJ")
        case .bermuda:
            return CountryIntentValue(value: "Bermudes", flag: "🇧🇲", code: "BM")
        case .bhutan:
            return CountryIntentValue(value: "Bhoutan", flag: "🇧🇹", code: "BT")
        case .bolivia:
            return CountryIntentValue(value: "Bolivie", flag: "🇧🇴", code: "BO")
        case .bonaireSaintEustacheAndSaba:
            return CountryIntentValue(value: "Bonaire, Saint-Eustache et Saba", flag: "🇧🇶", code: "BQ")
        case .bosniaAndHerzegovina:
            return CountryIntentValue(value: "Bosnie-Herzégovine", flag: "🇧🇦", code: "BA")
        case .botswana:
            return CountryIntentValue(value: "Botswana", flag: "🇧🇼", code: "BW")
        case .bouvetIsland:
            return CountryIntentValue(value: "Île Bouvet", flag: "🇧🇻", code: "BV")
        case .brazil:
            return CountryIntentValue(value: "Brésil", flag: "🇧🇷", code: "BR")
        case .britishIndianOceanTerritory:
            return CountryIntentValue(value: "Territoire britannique de l'océan Indien", flag: "🇮🇴", code: "IO")
        case .britishVirginIslands:
            return CountryIntentValue(value: "Îles Vierges britanniques", flag: "🇻🇬", code: "VG")
        case .bulgaria:
            return CountryIntentValue(value: "Bulgarie", flag: "🇧🇬", code: "BG")
        case .burkinaFaso:
            return CountryIntentValue(value: "Burkina Faso", flag: "🇧🇫", code: "BF")
        case .burundi:
            return CountryIntentValue(value: "Burundi", flag: "🇧🇮", code: "BI")
        case .cambodia:
            return CountryIntentValue(value: "Cambodge", flag: "🇰🇭", code: "KH")
        case .cameroon:
            return CountryIntentValue(value: "Cameroun", flag: "🇨🇲", code: "CM")
        case .canada:
            return CountryIntentValue(value: "Canada", flag: "🇨🇦", code: "CA")
        case .capeVerde:
            return CountryIntentValue(value: "Cap-Vert", flag: "🇨🇻", code: "CV")
        case .caymanIslands:
            return CountryIntentValue(value: "Îles Caïmans", flag: "🇰🇾", code: "KY")
        case .centralAfricanRepublic:
            return CountryIntentValue(value: "République centrafricaine", flag: "🇨🇫", code: "CF")
        case .chad:
            return CountryIntentValue(value: "Tchad", flag: "🇹🇩", code: "TD")
        case .chile:
            return CountryIntentValue(value: "Chili", flag: "🇨🇱", code: "CL")
        case .china:
            return CountryIntentValue(value: "Chine", flag: "🇨🇳", code: "CN")
        case .christmasIsland:
            return CountryIntentValue(value: "Île Christmas", flag: "🇨🇽", code: "CX")
        case .cocosIslands:
            return CountryIntentValue(value: "Îles Cocos", flag: "🇨🇨", code: "CC")
        case .comoros:
            return CountryIntentValue(value: "Comores", flag: "🇰🇲", code: "KM")
        case .congoBrazzaville:
            return CountryIntentValue(value: "Congo-Brazzaville", flag: "🇨🇬", code: "CG")
        case .congoKinshasa:
            return CountryIntentValue(value: "Congo-Kinshasa", flag: "🇨🇩", code: "CD")
        case .cookIslands:
            return CountryIntentValue(value: "Îles Cook", flag: "🇨🇰", code: "CK")
        case .costaRica:
            return CountryIntentValue(value: "Costa Rica", flag: "🇨🇷", code: "CR")
        case .curacao:
            return CountryIntentValue(value: "Curaçao", flag: "🇨🇼", code: "CW")
        case .cyprus:
            return CountryIntentValue(value: "Chypre", flag: "🇨🇾", code: "CY")
        case .czechia:
            return CountryIntentValue(value: "Tchéquie", flag: "🇨🇿", code: "CZ")
        case .denmark:
            return CountryIntentValue(value: "Danemark", flag: "🇩🇰", code: "DK")
        case .djibouti:
            return CountryIntentValue(value: "Djibouti", flag: "🇩🇯", code: "DJ")
        case .dominica:
            return CountryIntentValue(value: "Dominique", flag: "🇩🇲", code: "DM")
        case .dominicanRepublic:
            return CountryIntentValue(value: "République dominicaine", flag: "🇩🇴", code: "DO")
        case .eastTimor:
            return CountryIntentValue(value: "Timor oriental", flag: "🇹🇱", code: "TL")
        case .ecuador:
            return CountryIntentValue(value: "Équateur", flag: "🇪🇨", code: "EC")
        case .egypt:
            return CountryIntentValue(value: "Égypte", flag: "🇪🇬", code: "EG")
        case .elSalvador:
            return CountryIntentValue(value: "Salvador", flag: "🇸🇻", code: "SV")
        case .equatorialGuinea:
            return CountryIntentValue(value: "Guinée équatoriale", flag: "🇬🇶", code: "GQ")
        case .eritrea:
            return CountryIntentValue(value: "Érythrée", flag: "🇪🇷", code: "ER")
        case .estonia:
            return CountryIntentValue(value: "Estonie", flag: "🇪🇪", code: "EE")
        case .eswatini:
            return CountryIntentValue(value: "Eswatini", flag: "🇸🇿", code: "SZ")
        case .ethiopia:
            return CountryIntentValue(value: "Éthiopie", flag: "🇪🇹", code: "ET")
        case .falklandIslands:
            return CountryIntentValue(value: "Îles Malouines", flag: "🇫🇰", code: "FK")
        case .faroeIslands:
            return CountryIntentValue(value: "Îles Féroé", flag: "🇫🇴", code: "FO")
        case .fiji:
            return CountryIntentValue(value: "Fidji", flag: "🇫🇯", code: "FJ")
        case .finland:
            return CountryIntentValue(value: "Finlande", flag: "🇫🇮", code: "FI")
        case .france:
            return CountryIntentValue(value: "France", flag: "🇫🇷", code: "FR")
        case .frenchGuiana:
            return CountryIntentValue(value: "Guyane française", flag: "🇬🇫", code: "GF")
        case .frenchPolynesia:
            return CountryIntentValue(value: "Polynésie française", flag: "🇵🇫", code: "PF")
        case .frenchSouthernTerritories:
            return CountryIntentValue(value: "Terres australes françaises", flag: "🇹🇫", code: "TF")
        case .gabon:
            return CountryIntentValue(value: "Gabon", flag: "🇬🇦", code: "GA")
        case .gambia:
            return CountryIntentValue(value: "Gambie", flag: "🇬🇲", code: "GM")
        case .georgia:
            return CountryIntentValue(value: "Géorgie", flag: "🇬🇪", code: "GE")
        case .germany:
            return CountryIntentValue(value: "Allemagne", flag: "🇩🇪", code: "DE")
        case .ghana:
            return CountryIntentValue(value: "Ghana", flag: "🇬🇭", code: "GH")
        case .gibraltar:
            return CountryIntentValue(value: "Gibraltar", flag: "🇬🇮", code: "GI")
        case .greece:
            return CountryIntentValue(value: "Grèce", flag: "🇬🇷", code: "GR")
        case .greenland:
            return CountryIntentValue(value: "Groenland", flag: "🇬🇱", code: "GL")
        case .grenada:
            return CountryIntentValue(value: "Grenade", flag: "🇬🇩", code: "GD")
        case .guadeloupe:
            return CountryIntentValue(value: "Guadeloupe", flag: "🇬🇵", code: "GP")
        case .guam:
            return CountryIntentValue(value: "Guam", flag: "🇬🇺", code: "GU")
        case .guatemala:
            return CountryIntentValue(value: "Guatemala", flag: "🇬🇹", code: "GT")
        case .guernsey:
            return CountryIntentValue(value: "Guernesey", flag: "🇬🇬", code: "GG")
        case .guinea:
            return CountryIntentValue(value: "Guinée", flag: "🇬🇳", code: "GN")
        case .guineaBissau:
            return CountryIntentValue(value: "Guinée-Bissau", flag: "🇬🇼", code: "GW")
        case .guyana:
            return CountryIntentValue(value: "Guyana", flag: "🇬🇾", code: "GY")
        case .haiti:
            return CountryIntentValue(value: "Haïti", flag: "🇭🇹", code: "HT")
        case .heardAndMcDonaldIslands:
            return CountryIntentValue(value: "Îles Heard-et-MacDonald", flag: "🇭🇲", code: "HM")
        case .honduras:
            return CountryIntentValue(value: "Honduras", flag: "🇭🇳", code: "HN")
        case .hongKongSAR:
            return CountryIntentValue(value: "R.A.S. chinoise de Hong Kong", flag: "🇭🇰", code: "HK")
        case .hungary:
            return CountryIntentValue(value: "Hongrie", flag: "🇭🇺", code: "HU")
        case .iceland:
            return CountryIntentValue(value: "Islande", flag: "🇮🇸", code: "IS")
        case .india:
            return CountryIntentValue(value: "Inde", flag: "🇮🇳", code: "IN")
        case .indonesia:
            return CountryIntentValue(value: "Indonésie", flag: "🇮🇩", code: "ID")
        case .iran:
            return CountryIntentValue(value: "Iran", flag: "🇮🇷", code: "IR")
        case .iraq:
            return CountryIntentValue(value: "Irak", flag: "🇮🇶", code: "IQ")
        case .ireland:
            return CountryIntentValue(value: "Irlande", flag: "🇮🇪", code: "IE")
        case .isleOfMan:
            return CountryIntentValue(value: "Île de Man", flag: "🇮🇲", code: "IM")
        case .israel:
            return CountryIntentValue(value: "Israël", flag: "🇮🇱", code: "IL")
        case .italy:
            return CountryIntentValue(value: "Italie", flag: "🇮🇹", code: "IT")
        case .jamaica:
            return CountryIntentValue(value: "Jamaïque", flag: "🇯🇲", code: "JM")
        case .japan:
            return CountryIntentValue(value: "Japon", flag: "🇯🇵", code: "JP")
        case .jersey:
            return CountryIntentValue(value: "Jersey", flag: "🇯🇪", code: "JE")
        case .jordan:
            return CountryIntentValue(value: "Jordanie", flag: "🇯🇴", code: "JO")
        case .kazakhstan:
            return CountryIntentValue(value: "Kazakhstan", flag: "🇰🇿", code: "KZ")
        case .kenya:
            return CountryIntentValue(value: "Kenya", flag: "🇰🇪", code: "KE")
        case .kiribati:
            return CountryIntentValue(value: "Kiribati", flag: "🇰🇮", code: "KI")
        case .kosovo:
            return CountryIntentValue(value: "Kosovo", flag: "🇽🇰", code: "XK")
        case .kuwait:
            return CountryIntentValue(value: "Koweït", flag: "🇰🇼", code: "KW")
        case .kyrgyzstan:
            return CountryIntentValue(value: "Kirghizstan", flag: "🇰🇬", code: "KG")
        case .laos:
            return CountryIntentValue(value: "Laos", flag: "🇱🇦", code: "LA")
        case .latvia:
            return CountryIntentValue(value: "Lettonie", flag: "🇱🇻", code: "LV")
        case .lebanon:
            return CountryIntentValue(value: "Liban", flag: "🇱🇧", code: "LB")
        case .lesotho:
            return CountryIntentValue(value: "Lesotho", flag: "🇱🇸", code: "LS")
        case .liberia:
            return CountryIntentValue(value: "Liberia", flag: "🇱🇷", code: "LR")
        case .libya:
            return CountryIntentValue(value: "Libye", flag: "🇱🇾", code: "LY")
        case .liechtenstein:
            return CountryIntentValue(value: "Liechtenstein", flag: "🇱🇮", code: "LI")
        case .lithuania:
            return CountryIntentValue(value: "Lituanie", flag: "🇱🇹", code: "LT")
        case .luxembourg:
            return CountryIntentValue(value: "Luxembourg", flag: "🇱🇺", code: "LU")
        case .macaoSAR:
            return CountryIntentValue(value: "R.A.S. chinoise de Macao", flag: "🇲🇴", code: "MO")
        case .macedonia:
            return CountryIntentValue(value: "Macédoine du Nord", flag: "🇲🇰", code: "MK")
        case .madagascar:
            return CountryIntentValue(value: "Madagascar", flag: "🇲🇬", code: "MG")
        case .malawi:
            return CountryIntentValue(value: "Malawi", flag: "🇲🇼", code: "MW")
        case .malaysia:
            return CountryIntentValue(value: "Malaisie", flag: "🇲🇾", code: "MY")
        case .maldives:
            return CountryIntentValue(value: "Maldives", flag: "🇲🇻", code: "MV")
        case .mali:
            return CountryIntentValue(value: "Mali", flag: "🇲🇱", code: "ML")
        case .malta:
            return CountryIntentValue(value: "Malte", flag: "🇲🇹", code: "MT")
        case .marshallIslands:
            return CountryIntentValue(value: "Îles Marshall", flag: "🇲🇭", code: "MH")
        case .martinique:
            return CountryIntentValue(value: "Martinique", flag: "🇲🇶", code: "MQ")
        case .mauritania:
            return CountryIntentValue(value: "Mauritanie", flag: "🇲🇷", code: "MR")
        case .mauritius:
            return CountryIntentValue(value: "Maurice", flag: "🇲🇺", code: "MU")
        case .mayotte:
            return CountryIntentValue(value: "Mayotte", flag: "🇾🇹", code: "YT")
        case .mexico:
            return CountryIntentValue(value: "Mexique", flag: "🇲🇽", code: "MX")
        case .micronesia:
            return CountryIntentValue(value: "Micronésie", flag: "🇫🇲", code: "FM")
        case .moldova:
            return CountryIntentValue(value: "Moldavie", flag: "🇲🇩", code: "MD")
        case .monaco:
            return CountryIntentValue(value: "Monaco", flag: "🇲🇨", code: "MC")
        case .mongolia:
            return CountryIntentValue(value: "Mongolie", flag: "🇲🇳", code: "MN")
        case .montenegro:
            return CountryIntentValue(value: "Monténégro", flag: "🇲🇪", code: "ME")
        case .montserrat:
            return CountryIntentValue(value: "Montserrat", flag: "🇲🇸", code: "MS")
        case .morocco:
            return CountryIntentValue(value: "Maroc", flag: "🇲🇦", code: "MA")
        case .mozambique:
            return CountryIntentValue(value: "Mozambique", flag: "🇲🇿", code: "MZ")
        case .myanmar:
            return CountryIntentValue(value: "Myanmar (Birmanie)", flag: "🇲🇲", code: "MM")
        case .namibia:
            return CountryIntentValue(value: "Namibie", flag: "🇳🇦", code: "NA")
        case .nauru:
            return CountryIntentValue(value: "Nauru", flag: "🇳🇷", code: "NR")
        case .nepal:
            return CountryIntentValue(value: "Népal", flag: "🇳🇵", code: "NP")
        case .netherlands:
            return CountryIntentValue(value: "Pays-Bas", flag: "🇳🇱", code: "NL")
        case .netherlandsAntilles:
            return CountryIntentValue(value: "Pays-Bas caribéens", flag: "🇧🇶", code: "AN")
        case .newCaledonia:
            return CountryIntentValue(value: "Nouvelle-Calédonie", flag: "🇳🇨", code: "NC")
        case .newZealand:
            return CountryIntentValue(value: "Nouvelle-Zélande", flag: "🇳🇿", code: "NZ")
        case .nicaragua:
            return CountryIntentValue(value: "Nicaragua", flag: "🇳🇮", code: "NI")
        case .niger:
            return CountryIntentValue(value: "Niger", flag: "🇳🇪", code: "NE")
        case .nigeria:
            return CountryIntentValue(value: "Nigeria", flag: "🇳🇬", code: "NG")
        case .niue:
            return CountryIntentValue(value: "Niue", flag: "🇳🇺", code: "NU")
        case .norfolkIsland:
            return CountryIntentValue(value: "Île Norfolk", flag: "🇳🇫", code: "NF")
        case .northKorea:
            return CountryIntentValue(value: "Corée du Nord", flag: "🇰🇵", code: "KP")
        case .northernMarianaIslands:
            return CountryIntentValue(value: "Îles Mariannes du Nord", flag: "🇲🇵", code: "MP")
        case .norway:
            return CountryIntentValue(value: "Norvège", flag: "🇳🇴", code: "NO")
        case .oman:
            return CountryIntentValue(value: "Oman", flag: "🇴🇲", code: "OM")
        case .pakistan:
            return CountryIntentValue(value: "Pakistan", flag: "🇵🇰", code: "PK")
        case .palau:
            return CountryIntentValue(value: "Palau", flag: "🇵🇼", code: "PW")
        case .palestinianTerritories:
            return CountryIntentValue(value: "Palestine", flag: "🇵🇸", code: "PS")
        case .panama:
            return CountryIntentValue(value: "Panama", flag: "🇵🇦", code: "PA")
        case .papuaNewGuinea:
            return CountryIntentValue(value: "Papouasie-Nouvelle-Guinée", flag: "🇵🇬", code: "PG")
        case .paraguay:
            return CountryIntentValue(value: "Paraguay", flag: "🇵🇾", code: "PY")
        case .peru:
            return CountryIntentValue(value: "Pérou", flag: "🇵🇪", code: "PE")
        case .philippines:
            return CountryIntentValue(value: "Philippines", flag: "🇵🇭", code: "PH")
        case .pitcairnIslands:
            return CountryIntentValue(value: "Îles Pitcairn", flag: "🇵🇳", code: "PN")
        case .poland:
            return CountryIntentValue(value: "Pologne", flag: "🇵🇱", code: "PL")
        case .portugal:
            return CountryIntentValue(value: "Portugal", flag: "🇵🇹", code: "PT")
        case .puertoRico:
            return CountryIntentValue(value: "Porto Rico", flag: "🇵🇷", code: "PR")
        case .qatar:
            return CountryIntentValue(value: "Qatar", flag: "🇶🇦", code: "QA")
        case .reunion:
            return CountryIntentValue(value: "La Réunion", flag: "🇷🇪", code: "RE")
        case .romania:
            return CountryIntentValue(value: "Roumanie", flag: "🇷🇴", code: "RO")
        case .russia:
            return CountryIntentValue(value: "Russie", flag: "🇷🇺", code: "RU")
        case .rwanda:
            return CountryIntentValue(value: "Rwanda", flag: "🇷🇼", code: "RW")
        case .saintBarthelemy:
            return CountryIntentValue(value: "Saint-Barthélemy", flag: "🇧🇱", code: "BL")
        case .saintHelena:
            return CountryIntentValue(value: "Sainte-Hélène", flag: "🇸🇭", code: "SH")
        case .saintKittsAndNevis:
            return CountryIntentValue(value: "Saint-Christophe-et-Niévès", flag: "🇰🇳", code: "KN")
        case .saintLucia:
            return CountryIntentValue(value: "Sainte-Lucie", flag: "🇱🇨", code: "LC")
        case .saintPierreAndMiquelon:
            return CountryIntentValue(value: "Saint-Pierre-et-Miquelon", flag: "🇵🇲", code: "PM")
        case .saintVincentAndTheGrenadines:
            return CountryIntentValue(value: "Saint-Vincent-et-les Grenadines", flag: "🇻🇨", code: "VC")
        case .samoa:
            return CountryIntentValue(value: "Samoa", flag: "🇼🇸", code: "WS")
        case .sanMarino:
            return CountryIntentValue(value: "Saint-Marin", flag: "🇸🇲", code: "SM")
        case .saoTomeAndPrincipe:
            return CountryIntentValue(value: "Sao Tomé-et-Principe", flag: "🇸🇹", code: "ST")
        case .saudiArabia:
            return CountryIntentValue(value: "Arabie saoudite", flag: "🇸🇦", code: "SA")
        case .senegal:
            return CountryIntentValue(value: "Sénégal", flag: "🇸🇳", code: "SN")
        case .serbia:
            return CountryIntentValue(value: "Serbie", flag: "🇷🇸", code: "RS")
        case .seychelles:
            return CountryIntentValue(value: "Seychelles", flag: "🇸🇨", code: "SC")
        case .sierraLeone:
            return CountryIntentValue(value: "Sierra Leone", flag: "🇸🇱", code: "SL")
        case .singapore:
            return CountryIntentValue(value: "Singapour", flag: "🇸🇬", code: "SG")
        case .slovakia:
            return CountryIntentValue(value: "Slovaquie", flag: "🇸🇰", code: "SK")
        case .slovenia:
            return CountryIntentValue(value: "Slovénie", flag: "🇸🇮", code: "SI")
        case .solomonIslands:
            return CountryIntentValue(value: "Îles Salomon", flag: "🇸🇧", code: "SB")
        case .somalia:
            return CountryIntentValue(value: "Somalie", flag: "🇸🇴", code: "SO")
        case .somaliland:
            return CountryIntentValue(value: "Somaliland", flag: "🇸🇴", code: "SOL")
        case .southAfrica:
            return CountryIntentValue(value: "Afrique du Sud", flag: "🇿🇦", code: "ZA")
        case .southKorea:
            return CountryIntentValue(value: "Corée du Sud", flag: "🇰🇷", code: "KR")
        case .southSudan:
            return CountryIntentValue(value: "Soudan du Sud", flag: "🇸🇸", code: "SS")
        case .spain:
            return CountryIntentValue(value: "Espagne", flag: "🇪🇸", code: "ES")
        case .sriLanka:
            return CountryIntentValue(value: "Sri Lanka", flag: "🇱🇰", code: "LK")
        case .sudan:
            return CountryIntentValue(value: "Soudan", flag: "🇸🇩", code: "SD")
        case .suriname:
            return CountryIntentValue(value: "Suriname", flag: "🇸🇷", code: "SR")
        case .svalbardAndJanMayen:
            return CountryIntentValue(value: "Svalbard et Jan Mayen", flag: "🇸🇯", code: "SJ")
        case .swaziland:
            return CountryIntentValue(value: "Swaziland", flag: "🇸🇿", code: "SWZ")
        case .sweden:
            return CountryIntentValue(value: "Suède", flag: "🇸🇪", code: "SE")
        case .switzerland:
            return CountryIntentValue(value: "Suisse", flag: "🇨🇭", code: "CH")
        case .syria:
            return CountryIntentValue(value: "Syrie", flag: "🇸🇾", code: "SY")
        case .taiwan:
            return CountryIntentValue(value: "Taïwan", flag: "🇹🇼", code: "TW")
        case .tajikistan:
            return CountryIntentValue(value: "Tadjikistan", flag: "🇹🇯", code: "TJ")
        case .tanzania:
            return CountryIntentValue(value: "Tanzanie", flag: "🇹🇿", code: "TZ")
        case .thailand:
            return CountryIntentValue(value: "Thaïlande", flag: "🇹🇭", code: "TH")
        case .togo:
            return CountryIntentValue(value: "Togo", flag: "🇹🇬", code: "TG")
        case .tokelau:
            return CountryIntentValue(value: "Tokelau", flag: "🇹🇰", code: "TK")
        case .tonga:
            return CountryIntentValue(value: "Tonga", flag: "🇹🇴", code: "TO")
        case .transnistrie:
            return CountryIntentValue(value: "Transnistrie", flag: "🇲🇩", code: "PMR")
        case .trinidadAndTobago:
            return CountryIntentValue(value: "Trinité-et-Tobago", flag: "🇹🇹", code: "TT")
        case .tunisia:
            return CountryIntentValue(value: "Tunisie", flag: "🇹🇳", code: "TN")
        case .turkey:
            return CountryIntentValue(value: "Turquie", flag: "🇹🇷", code: "TR")
        case .turkmenistan:
            return CountryIntentValue(value: "Turkménistan", flag: "🇹🇲", code: "TM")
        case .turksAndCaicosIslands:
            return CountryIntentValue(value: "Îles Turques-et-Caïques", flag: "🇹🇨", code: "TC")
        case .tuvalu:
            return CountryIntentValue(value: "Tuvalu", flag: "🇹🇻", code: "TV")
        case .uganda:
            return CountryIntentValue(value: "Ouganda", flag: "🇺🇬", code: "UG")
        case .ukraine:
            return CountryIntentValue(value: "Ukraine", flag: "🇺🇦", code: "UA")
        case .unitedArabEmirates:
            return CountryIntentValue(value: "Émirats arabes unis", flag: "🇦🇪", code: "AE")
        case .unitedKingdom:
            return CountryIntentValue(value: "Royaume-Uni", flag: "🇬🇧", code: "GB")
        case .unitedStates:
            return CountryIntentValue(value: "États-Unis", flag: "🇺🇸", code: "US")
        case .usOutlyingIslands:
            return CountryIntentValue(value: "Îles mineures éloignées des États-Unis", flag: "🇺🇲", code: "UM")
        case .usVirginIslands:
            return CountryIntentValue(value: "Îles Vierges des États-Unis", flag: "🇻🇮", code: "VI")
        case .uruguay:
            return CountryIntentValue(value: "Uruguay", flag: "🇺🇾", code: "UY")
        case .uzbekistan:
            return CountryIntentValue(value: "Ouzbékistan", flag: "🇺🇿", code: "UZ")
        case .vanuatu:
            return CountryIntentValue(value: "Vanuatu", flag: "🇻🇺", code: "VU")
        case .vaticanCityState:
            return CountryIntentValue(value: "État de la Cité du Vatican", flag: "🇻🇦", code: "VA")
        case .venezuela:
            return CountryIntentValue(value: "Venezuela", flag: "🇻🇪", code: "VE")
        case .vietnam:
            return CountryIntentValue(value: "Viêt Nam", flag: "🇻🇳", code: "VN")
        case .wallisAndFutuna:
            return CountryIntentValue(value: "Wallis-et-Futuna", flag: "🇼🇫", code: "WF")
        case .westernSahara:
            return CountryIntentValue(value: "Sahara occidental", flag: "🇪🇭", code: "EH")
        case .yemen:
            return CountryIntentValue(value: "Yémen", flag: "🇾🇪", code: "YE")
        case .zambia:
            return CountryIntentValue(value: "Zambie", flag: "🇿🇲", code: "ZM")
        case .zimbabwe:
            return CountryIntentValue(value: "Zimbabwe", flag: "🇿🇼", code: "ZW")
        }
    }
}

struct CountryIntentValue {
    let value: String
    let flag: String
    let code: String
}

extension Country {
    func toCountryIntent() -> CountryIntent? {
        return CountryIntent.allCases.first { countryIntent in
            countryIntent.value.code == self.code
        }
    }
}

package de.niklasbednarczyk.nbweather.core.common.locale

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe

enum class NBCountryType {

    AFGHANISTAN,
    ALAND_ISLANDS,
    ALBANIA,
    ALGERIA,
    AMERICAN_SAMOA,
    ANDORRA,
    ANGOLA,
    ANGUILLA,
    ANTARCTICA,
    ANTIGUA_AND_BARBUDA,
    ARGENTINA,
    ARMENIA,
    ARUBA,
    AUSTRALIA,
    AUSTRIA,
    AZERBAIJAN,
    BAHAMAS,
    BAHRAIN,
    BANGLADESH,
    BARBADOS,
    BELARUS,
    BELGIUM,
    BELIZE,
    BENIN,
    BERMUDA,
    BHUTAN,
    BOLIVIA,
    BOSNIA_AND_HERZEGOVINA,
    BOTSWANA,
    BOUVET_ISLAND,
    BRAZIL,
    BRITISH_INDIAN_OCEAN_TERRITORY,
    BRITISH_VIRGIN_ISLANDS,
    BRUNEI,
    BULGARIA,
    BURKINA_FASO,
    BURUNDI,
    CAMBODIA,
    CAMEROON,
    CANADA,
    CAPE_VERDE,
    CARIBBEAN_NETHERLANDS,
    CAYMAN_ISLANDS,
    CENTRAL_AFRICAN_REPUBLIC,
    CHAD,
    CHILE,
    CHINA,
    CHRISTMAS_ISLAND,
    COCOS_KEELING_ISLANDS,
    COLOMBIA,
    COMOROS,
    COOK_ISLANDS,
    COSTA_RICA,
    CROATIA,
    CUBA,
    CURACAO,
    CYPRUS,
    CZECHIA,
    DENMARK,
    DJIBOUTI,
    DOMINICA,
    DOMINICAN_REPUBLIC,
    DR_CONGO,
    ECUADOR,
    EGYPT,
    EL_SALVADOR,
    EQUATORIAL_GUINEA,
    ERITREA,
    ESTONIA,
    ESWATINI,
    ETHIOPIA,
    FALKLAND_ISLANDS,
    FAROE_ISLANDS,
    FIJI,
    FINLAND,
    FRANCE,
    FRENCH_GUIANA,
    FRENCH_POLYNESIA,
    FRENCH_SOUTHERN_AND_ANTARCTIC_LANDS,
    GABON,
    GAMBIA,
    GEORGIA,
    GERMANY,
    GHANA,
    GIBRALTAR,
    GREECE,
    GREENLAND,
    GRENADA,
    GUADELOUPE,
    GUAM,
    GUATEMALA,
    GUERNSEY,
    GUINEA,
    GUINEA_BISSAU,
    GUYANA,
    HAITI,
    HEARD_ISLAND_AND_MCDONALD_ISLANDS,
    HONDURAS,
    HONG_KONG,
    HUNGARY,
    ICELAND,
    INDIA,
    INDONESIA,
    IRAN,
    IRAQ,
    IRELAND,
    ISLE_OF_MAN,
    ISRAEL,
    ITALY,
    IVORY_COAST,
    JAMAICA,
    JAPAN,
    JERSEY,
    JORDAN,
    KAZAKHSTAN,
    KENYA,
    KIRIBATI,
    KOSOVO,
    KUWAIT,
    KYRGYZSTAN,
    LAOS,
    LATVIA,
    LEBANON,
    LESOTHO,
    LIBERIA,
    LIBYA,
    LIECHTENSTEIN,
    LITHUANIA,
    LUXEMBOURG,
    MACAU,
    MADAGASCAR,
    MALAWI,
    MALAYSIA,
    MALDIVES,
    MALI,
    MALTA,
    MARSHALL_ISLANDS,
    MARTINIQUE,
    MAURITANIA,
    MAURITIUS,
    MAYOTTE,
    MEXICO,
    MICRONESIA,
    MOLDOVA,
    MONACO,
    MONGOLIA,
    MONTENEGRO,
    MONTSERRAT,
    MOROCCO,
    MOZAMBIQUE,
    MYANMAR,
    NAMIBIA,
    NAURU,
    NEPAL,
    NETHERLANDS,
    NEW_CALEDONIA,
    NEW_ZEALAND,
    NICARAGUA,
    NIGER,
    NIGERIA,
    NIUE,
    NORFOLK_ISLAND,
    NORTHERN_MARIANA_ISLANDS,
    NORTH_KOREA,
    NORTH_MACEDONIA,
    NORWAY,
    OMAN,
    PAKISTAN,
    PALAU,
    PALESTINE,
    PANAMA,
    PAPUA_NEW_GUINEA,
    PARAGUAY,
    PERU,
    PHILIPPINES,
    PITCAIRN_ISLANDS,
    POLAND,
    PORTUGAL,
    PUERTO_RICO,
    QATAR,
    REPUBLIC_OF_THE_CONGO,
    REUNION,
    ROMANIA,
    RUSSIA,
    RWANDA,
    SAINT_BARTHELEMY,
    SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA,
    SAINT_KITTS_AND_NEVIS,
    SAINT_LUCIA,
    SAINT_MARTIN,
    SAINT_PIERRE_AND_MIQUELON,
    SAINT_VINCENT_AND_THE_GRENADINES,
    SAMOA,
    SAN_MARINO,
    SAO_TOME_AND_PRINCIPE,
    SAUDI_ARABIA,
    SENEGAL,
    SERBIA,
    SEYCHELLES,
    SIERRA_LEONE,
    SINGAPORE,
    SINT_MAARTEN,
    SLOVAKIA,
    SLOVENIA,
    SOLOMON_ISLANDS,
    SOMALIA,
    SOUTH_AFRICA,
    SOUTH_GEORGIA,
    SOUTH_KOREA,
    SOUTH_SUDAN,
    SPAIN,
    SRI_LANKA,
    SUDAN,
    SURINAME,
    SVALBARD_AND_JAN_MAYEN,
    SWEDEN,
    SWITZERLAND,
    SYRIA,
    TAIWAN,
    TAJIKISTAN,
    TANZANIA,
    THAILAND,
    TIMOR_LESTE,
    TOGO,
    TOKELAU,
    TONGA,
    TRINIDAD_AND_TOBAGO,
    TUNISIA,
    TURKEY,
    TURKMENISTAN,
    TURKS_AND_CAICOS_ISLANDS,
    TUVALU,
    UGANDA,
    UKRAINE,
    UNITED_ARAB_EMIRATES,
    UNITED_KINGDOM,
    UNITED_STATES,
    UNITED_STATES_MINOR_OUTLYING_ISLANDS,
    UNITED_STATES_VIRGIN_ISLANDS,
    URUGUAY,
    UZBEKISTAN,
    VANUATU,
    VATICAN_CITY,
    VENEZUELA,
    VIETNAM,
    WALLIS_AND_FUTUNA,
    WESTERN_SAHARA,
    YEMEN,
    ZAMBIA,
    ZIMBABWE;

    val country: String
        get() = when (this) {
            AFGHANISTAN -> "AF"
            ALAND_ISLANDS -> "AX"
            ALBANIA -> "AL"
            ALGERIA -> "DZ"
            AMERICAN_SAMOA -> "AS"
            ANDORRA -> "AD"
            ANGOLA -> "AO"
            ANGUILLA -> "AI"
            ANTARCTICA -> "AQ"
            ANTIGUA_AND_BARBUDA -> "AG"
            ARGENTINA -> "AR"
            ARMENIA -> "AM"
            ARUBA -> "AW"
            AUSTRALIA -> "AU"
            AUSTRIA -> "AT"
            AZERBAIJAN -> "AZ"
            BAHAMAS -> "BS"
            BAHRAIN -> "BH"
            BANGLADESH -> "BD"
            BARBADOS -> "BB"
            BELARUS -> "BY"
            BELGIUM -> "BE"
            BELIZE -> "BZ"
            BENIN -> "BJ"
            BERMUDA -> "BM"
            BHUTAN -> "BT"
            BOLIVIA -> "BO"
            BOSNIA_AND_HERZEGOVINA -> "BA"
            BOTSWANA -> "BW"
            BOUVET_ISLAND -> "BV"
            BRAZIL -> "BR"
            BRITISH_INDIAN_OCEAN_TERRITORY -> "IO"
            BRITISH_VIRGIN_ISLANDS -> "VG"
            BRUNEI -> "BN"
            BULGARIA -> "BG"
            BURKINA_FASO -> "BF"
            BURUNDI -> "BI"
            CAMBODIA -> "KH"
            CAMEROON -> "CM"
            CANADA -> "CA"
            CAPE_VERDE -> "CV"
            CARIBBEAN_NETHERLANDS -> "BQ"
            CAYMAN_ISLANDS -> "KY"
            CENTRAL_AFRICAN_REPUBLIC -> "CF"
            CHAD -> "TD"
            CHILE -> "CL"
            CHINA -> "CN"
            CHRISTMAS_ISLAND -> "CX"
            COCOS_KEELING_ISLANDS -> "CC"
            COLOMBIA -> "CO"
            COMOROS -> "KM"
            COOK_ISLANDS -> "CK"
            COSTA_RICA -> "CR"
            CROATIA -> "HR"
            CUBA -> "CU"
            CURACAO -> "CW"
            CYPRUS -> "CY"
            CZECHIA -> "CZ"
            DENMARK -> "DK"
            DJIBOUTI -> "DJ"
            DOMINICA -> "DM"
            DOMINICAN_REPUBLIC -> "DO"
            DR_CONGO -> "CD"
            ECUADOR -> "EC"
            EGYPT -> "EG"
            EL_SALVADOR -> "SV"
            EQUATORIAL_GUINEA -> "GQ"
            ERITREA -> "ER"
            ESTONIA -> "EE"
            ESWATINI -> "SZ"
            ETHIOPIA -> "ET"
            FALKLAND_ISLANDS -> "FK"
            FAROE_ISLANDS -> "FO"
            FIJI -> "FJ"
            FINLAND -> "FI"
            FRANCE -> "FR"
            FRENCH_GUIANA -> "GF"
            FRENCH_POLYNESIA -> "PF"
            FRENCH_SOUTHERN_AND_ANTARCTIC_LANDS -> "TF"
            GABON -> "GA"
            GAMBIA -> "GM"
            GEORGIA -> "GE"
            GERMANY -> "DE"
            GHANA -> "GH"
            GIBRALTAR -> "GI"
            GREECE -> "GR"
            GREENLAND -> "GL"
            GRENADA -> "GD"
            GUADELOUPE -> "GP"
            GUAM -> "GU"
            GUATEMALA -> "GT"
            GUERNSEY -> "GG"
            GUINEA -> "GN"
            GUINEA_BISSAU -> "GW"
            GUYANA -> "GY"
            HAITI -> "HT"
            HEARD_ISLAND_AND_MCDONALD_ISLANDS -> "HM"
            HONDURAS -> "HN"
            HONG_KONG -> "HK"
            HUNGARY -> "HU"
            ICELAND -> "IS"
            INDIA -> "IN"
            INDONESIA -> "ID"
            IRAN -> "IR"
            IRAQ -> "IQ"
            IRELAND -> "IE"
            ISLE_OF_MAN -> "IM"
            ISRAEL -> "IL"
            ITALY -> "IT"
            IVORY_COAST -> "CI"
            JAMAICA -> "JM"
            JAPAN -> "JP"
            JERSEY -> "JE"
            JORDAN -> "JO"
            KAZAKHSTAN -> "KZ"
            KENYA -> "KE"
            KIRIBATI -> "KI"
            KOSOVO -> "XK"
            KUWAIT -> "KW"
            KYRGYZSTAN -> "KG"
            LAOS -> "LA"
            LATVIA -> "LV"
            LEBANON -> "LB"
            LESOTHO -> "LS"
            LIBERIA -> "LR"
            LIBYA -> "LY"
            LIECHTENSTEIN -> "LI"
            LITHUANIA -> "LT"
            LUXEMBOURG -> "LU"
            MACAU -> "MO"
            MADAGASCAR -> "MG"
            MALAWI -> "MW"
            MALAYSIA -> "MY"
            MALDIVES -> "MV"
            MALI -> "ML"
            MALTA -> "MT"
            MARSHALL_ISLANDS -> "MH"
            MARTINIQUE -> "MQ"
            MAURITANIA -> "MR"
            MAURITIUS -> "MU"
            MAYOTTE -> "YT"
            MEXICO -> "MX"
            MICRONESIA -> "FM"
            MOLDOVA -> "MD"
            MONACO -> "MC"
            MONGOLIA -> "MN"
            MONTENEGRO -> "ME"
            MONTSERRAT -> "MS"
            MOROCCO -> "MA"
            MOZAMBIQUE -> "MZ"
            MYANMAR -> "MM"
            NAMIBIA -> "NA"
            NAURU -> "NR"
            NEPAL -> "NP"
            NETHERLANDS -> "NL"
            NEW_CALEDONIA -> "NC"
            NEW_ZEALAND -> "NZ"
            NICARAGUA -> "NI"
            NIGER -> "NE"
            NIGERIA -> "NG"
            NIUE -> "NU"
            NORFOLK_ISLAND -> "NF"
            NORTHERN_MARIANA_ISLANDS -> "MP"
            NORTH_KOREA -> "KP"
            NORTH_MACEDONIA -> "MK"
            NORWAY -> "NO"
            OMAN -> "OM"
            PAKISTAN -> "PK"
            PALAU -> "PW"
            PALESTINE -> "PS"
            PANAMA -> "PA"
            PAPUA_NEW_GUINEA -> "PG"
            PARAGUAY -> "PY"
            PERU -> "PE"
            PHILIPPINES -> "PH"
            PITCAIRN_ISLANDS -> "PN"
            POLAND -> "PL"
            PORTUGAL -> "PT"
            PUERTO_RICO -> "PR"
            QATAR -> "QA"
            REPUBLIC_OF_THE_CONGO -> "CG"
            REUNION -> "RE"
            ROMANIA -> "RO"
            RUSSIA -> "RU"
            RWANDA -> "RW"
            SAINT_BARTHELEMY -> "BL"
            SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA -> "SH"
            SAINT_KITTS_AND_NEVIS -> "KN"
            SAINT_LUCIA -> "LC"
            SAINT_MARTIN -> "MF"
            SAINT_PIERRE_AND_MIQUELON -> "PM"
            SAINT_VINCENT_AND_THE_GRENADINES -> "VC"
            SAMOA -> "WS"
            SAN_MARINO -> "SM"
            SAO_TOME_AND_PRINCIPE -> "ST"
            SAUDI_ARABIA -> "SA"
            SENEGAL -> "SN"
            SERBIA -> "RS"
            SEYCHELLES -> "SC"
            SIERRA_LEONE -> "SL"
            SINGAPORE -> "SG"
            SINT_MAARTEN -> "SX"
            SLOVAKIA -> "SK"
            SLOVENIA -> "SI"
            SOLOMON_ISLANDS -> "SB"
            SOMALIA -> "SO"
            SOUTH_AFRICA -> "ZA"
            SOUTH_GEORGIA -> "GS"
            SOUTH_KOREA -> "KR"
            SOUTH_SUDAN -> "SS"
            SPAIN -> "ES"
            SRI_LANKA -> "LK"
            SUDAN -> "SD"
            SURINAME -> "SR"
            SVALBARD_AND_JAN_MAYEN -> "SJ"
            SWEDEN -> "SE"
            SWITZERLAND -> "CH"
            SYRIA -> "SY"
            TAIWAN -> "TW"
            TAJIKISTAN -> "TJ"
            TANZANIA -> "TZ"
            THAILAND -> "TH"
            TIMOR_LESTE -> "TL"
            TOGO -> "TG"
            TOKELAU -> "TK"
            TONGA -> "TO"
            TRINIDAD_AND_TOBAGO -> "TT"
            TUNISIA -> "TN"
            TURKEY -> "TR"
            TURKMENISTAN -> "TM"
            TURKS_AND_CAICOS_ISLANDS -> "TC"
            TUVALU -> "TV"
            UGANDA -> "UG"
            UKRAINE -> "UA"
            UNITED_ARAB_EMIRATES -> "AE"
            UNITED_KINGDOM -> "GB"
            UNITED_STATES -> "US"
            UNITED_STATES_MINOR_OUTLYING_ISLANDS -> "UM"
            UNITED_STATES_VIRGIN_ISLANDS -> "VI"
            URUGUAY -> "UY"
            UZBEKISTAN -> "UZ"
            VANUATU -> "VU"
            VATICAN_CITY -> "VA"
            VENEZUELA -> "VE"
            VIETNAM -> "VN"
            WALLIS_AND_FUTUNA -> "WF"
            WESTERN_SAHARA -> "EH"
            YEMEN -> "YE"
            ZAMBIA -> "ZM"
            ZIMBABWE -> "ZW"
        }

    companion object {

        fun from(
            country: String?
        ): NBCountryType? {
            return nbNullSafe(country) { c ->
                entries.find { countryType ->
                    countryType.country == c
                }
            }
        }

    }

}
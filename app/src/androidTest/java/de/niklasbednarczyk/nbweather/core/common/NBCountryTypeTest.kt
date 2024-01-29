package de.niklasbednarczyk.nbweather.core.common

import de.niklasbednarczyk.nbweather.core.common.locale.NBCountryType
import de.niklasbednarczyk.nbweather.test.common.tests.NBTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NBCountryTypeTest : NBTest {

    @Test
    fun country_AD_shouldConvertCorrectly() {
        testCountry(
            country = "AD",
            expectedCountryType = NBCountryType.ANDORRA
        )
    }

    @Test
    fun country_AE_shouldConvertCorrectly() {
        testCountry(
            country = "AE",
            expectedCountryType = NBCountryType.UNITED_ARAB_EMIRATES
        )
    }

    @Test
    fun country_AF_shouldConvertCorrectly() {
        testCountry(
            country = "AF",
            expectedCountryType = NBCountryType.AFGHANISTAN
        )
    }

    @Test
    fun country_AG_shouldConvertCorrectly() {
        testCountry(
            country = "AG",
            expectedCountryType = NBCountryType.ANTIGUA_AND_BARBUDA
        )
    }

    @Test
    fun country_AI_shouldConvertCorrectly() {
        testCountry(
            country = "AI",
            expectedCountryType = NBCountryType.ANGUILLA
        )
    }

    @Test
    fun country_AL_shouldConvertCorrectly() {
        testCountry(
            country = "AL",
            expectedCountryType = NBCountryType.ALBANIA
        )
    }

    @Test
    fun country_AM_shouldConvertCorrectly() {
        testCountry(
            country = "AM",
            expectedCountryType = NBCountryType.ARMENIA
        )
    }

    @Test
    fun country_AO_shouldConvertCorrectly() {
        testCountry(
            country = "AO",
            expectedCountryType = NBCountryType.ANGOLA
        )
    }

    @Test
    fun country_AQ_shouldConvertCorrectly() {
        testCountry(
            country = "AQ",
            expectedCountryType = NBCountryType.ANTARCTICA
        )
    }

    @Test
    fun country_AR_shouldConvertCorrectly() {
        testCountry(
            country = "AR",
            expectedCountryType = NBCountryType.ARGENTINA
        )
    }

    @Test
    fun country_AS_shouldConvertCorrectly() {
        testCountry(
            country = "AS",
            expectedCountryType = NBCountryType.AMERICAN_SAMOA
        )
    }

    @Test
    fun country_AT_shouldConvertCorrectly() {
        testCountry(
            country = "AT",
            expectedCountryType = NBCountryType.AUSTRIA
        )
    }

    @Test
    fun country_AU_shouldConvertCorrectly() {
        testCountry(
            country = "AU",
            expectedCountryType = NBCountryType.AUSTRALIA
        )
    }

    @Test
    fun country_AW_shouldConvertCorrectly() {
        testCountry(
            country = "AW",
            expectedCountryType = NBCountryType.ARUBA
        )
    }

    @Test
    fun country_AX_shouldConvertCorrectly() {
        testCountry(
            country = "AX",
            expectedCountryType = NBCountryType.ALAND_ISLANDS
        )
    }

    @Test
    fun country_AZ_shouldConvertCorrectly() {
        testCountry(
            country = "AZ",
            expectedCountryType = NBCountryType.AZERBAIJAN
        )
    }

    @Test
    fun country_BA_shouldConvertCorrectly() {
        testCountry(
            country = "BA",
            expectedCountryType = NBCountryType.BOSNIA_AND_HERZEGOVINA
        )
    }

    @Test
    fun country_BB_shouldConvertCorrectly() {
        testCountry(
            country = "BB",
            expectedCountryType = NBCountryType.BARBADOS
        )
    }

    @Test
    fun country_BD_shouldConvertCorrectly() {
        testCountry(
            country = "BD",
            expectedCountryType = NBCountryType.BANGLADESH
        )
    }

    @Test
    fun country_BE_shouldConvertCorrectly() {
        testCountry(
            country = "BE",
            expectedCountryType = NBCountryType.BELGIUM
        )
    }

    @Test
    fun country_BF_shouldConvertCorrectly() {
        testCountry(
            country = "BF",
            expectedCountryType = NBCountryType.BURKINA_FASO
        )
    }

    @Test
    fun country_BG_shouldConvertCorrectly() {
        testCountry(
            country = "BG",
            expectedCountryType = NBCountryType.BULGARIA
        )
    }

    @Test
    fun country_BH_shouldConvertCorrectly() {
        testCountry(
            country = "BH",
            expectedCountryType = NBCountryType.BAHRAIN
        )
    }

    @Test
    fun country_BI_shouldConvertCorrectly() {
        testCountry(
            country = "BI",
            expectedCountryType = NBCountryType.BURUNDI
        )
    }

    @Test
    fun country_BJ_shouldConvertCorrectly() {
        testCountry(
            country = "BJ",
            expectedCountryType = NBCountryType.BENIN
        )
    }

    @Test
    fun country_BL_shouldConvertCorrectly() {
        testCountry(
            country = "BL",
            expectedCountryType = NBCountryType.SAINT_BARTHELEMY
        )
    }

    @Test
    fun country_BM_shouldConvertCorrectly() {
        testCountry(
            country = "BM",
            expectedCountryType = NBCountryType.BERMUDA
        )
    }

    @Test
    fun country_BN_shouldConvertCorrectly() {
        testCountry(
            country = "BN",
            expectedCountryType = NBCountryType.BRUNEI
        )
    }

    @Test
    fun country_BO_shouldConvertCorrectly() {
        testCountry(
            country = "BO",
            expectedCountryType = NBCountryType.BOLIVIA
        )
    }

    @Test
    fun country_BQ_shouldConvertCorrectly() {
        testCountry(
            country = "BQ",
            expectedCountryType = NBCountryType.CARIBBEAN_NETHERLANDS
        )
    }

    @Test
    fun country_BR_shouldConvertCorrectly() {
        testCountry(
            country = "BR",
            expectedCountryType = NBCountryType.BRAZIL
        )
    }

    @Test
    fun country_BS_shouldConvertCorrectly() {
        testCountry(
            country = "BS",
            expectedCountryType = NBCountryType.BAHAMAS
        )
    }

    @Test
    fun country_BT_shouldConvertCorrectly() {
        testCountry(
            country = "BT",
            expectedCountryType = NBCountryType.BHUTAN
        )
    }

    @Test
    fun country_BV_shouldConvertCorrectly() {
        testCountry(
            country = "BV",
            expectedCountryType = NBCountryType.BOUVET_ISLAND
        )
    }

    @Test
    fun country_BW_shouldConvertCorrectly() {
        testCountry(
            country = "BW",
            expectedCountryType = NBCountryType.BOTSWANA
        )
    }

    @Test
    fun country_BY_shouldConvertCorrectly() {
        testCountry(
            country = "BY",
            expectedCountryType = NBCountryType.BELARUS
        )
    }

    @Test
    fun country_BZ_shouldConvertCorrectly() {
        testCountry(
            country = "BZ",
            expectedCountryType = NBCountryType.BELIZE
        )
    }

    @Test
    fun country_CA_shouldConvertCorrectly() {
        testCountry(
            country = "CA",
            expectedCountryType = NBCountryType.CANADA
        )
    }

    @Test
    fun country_CC_shouldConvertCorrectly() {
        testCountry(
            country = "CC",
            expectedCountryType = NBCountryType.COCOS_KEELING_ISLANDS
        )
    }

    @Test
    fun country_CD_shouldConvertCorrectly() {
        testCountry(
            country = "CD",
            expectedCountryType = NBCountryType.DR_CONGO
        )
    }

    @Test
    fun country_CF_shouldConvertCorrectly() {
        testCountry(
            country = "CF",
            expectedCountryType = NBCountryType.CENTRAL_AFRICAN_REPUBLIC
        )
    }

    @Test
    fun country_CG_shouldConvertCorrectly() {
        testCountry(
            country = "CG",
            expectedCountryType = NBCountryType.REPUBLIC_OF_THE_CONGO
        )
    }

    @Test
    fun country_CH_shouldConvertCorrectly() {
        testCountry(
            country = "CH",
            expectedCountryType = NBCountryType.SWITZERLAND
        )
    }

    @Test
    fun country_CI_shouldConvertCorrectly() {
        testCountry(
            country = "CI",
            expectedCountryType = NBCountryType.IVORY_COAST
        )
    }

    @Test
    fun country_CK_shouldConvertCorrectly() {
        testCountry(
            country = "CK",
            expectedCountryType = NBCountryType.COOK_ISLANDS
        )
    }

    @Test
    fun country_CL_shouldConvertCorrectly() {
        testCountry(
            country = "CL",
            expectedCountryType = NBCountryType.CHILE
        )
    }

    @Test
    fun country_CM_shouldConvertCorrectly() {
        testCountry(
            country = "CM",
            expectedCountryType = NBCountryType.CAMEROON
        )
    }

    @Test
    fun country_CN_shouldConvertCorrectly() {
        testCountry(
            country = "CN",
            expectedCountryType = NBCountryType.CHINA
        )
    }

    @Test
    fun country_CO_shouldConvertCorrectly() {
        testCountry(
            country = "CO",
            expectedCountryType = NBCountryType.COLOMBIA
        )
    }

    @Test
    fun country_CR_shouldConvertCorrectly() {
        testCountry(
            country = "CR",
            expectedCountryType = NBCountryType.COSTA_RICA
        )
    }

    @Test
    fun country_CU_shouldConvertCorrectly() {
        testCountry(
            country = "CU",
            expectedCountryType = NBCountryType.CUBA
        )
    }

    @Test
    fun country_CV_shouldConvertCorrectly() {
        testCountry(
            country = "CV",
            expectedCountryType = NBCountryType.CAPE_VERDE
        )
    }

    @Test
    fun country_CW_shouldConvertCorrectly() {
        testCountry(
            country = "CW",
            expectedCountryType = NBCountryType.CURACAO
        )
    }

    @Test
    fun country_CX_shouldConvertCorrectly() {
        testCountry(
            country = "CX",
            expectedCountryType = NBCountryType.CHRISTMAS_ISLAND
        )
    }

    @Test
    fun country_CY_shouldConvertCorrectly() {
        testCountry(
            country = "CY",
            expectedCountryType = NBCountryType.CYPRUS
        )
    }

    @Test
    fun country_CZ_shouldConvertCorrectly() {
        testCountry(
            country = "CZ",
            expectedCountryType = NBCountryType.CZECHIA
        )
    }

    @Test
    fun country_DE_shouldConvertCorrectly() {
        testCountry(
            country = "DE",
            expectedCountryType = NBCountryType.GERMANY
        )
    }

    @Test
    fun country_DJ_shouldConvertCorrectly() {
        testCountry(
            country = "DJ",
            expectedCountryType = NBCountryType.DJIBOUTI
        )
    }

    @Test
    fun country_DK_shouldConvertCorrectly() {
        testCountry(
            country = "DK",
            expectedCountryType = NBCountryType.DENMARK
        )
    }

    @Test
    fun country_DM_shouldConvertCorrectly() {
        testCountry(
            country = "DM",
            expectedCountryType = NBCountryType.DOMINICA
        )
    }

    @Test
    fun country_DO_shouldConvertCorrectly() {
        testCountry(
            country = "DO",
            expectedCountryType = NBCountryType.DOMINICAN_REPUBLIC
        )
    }

    @Test
    fun country_DZ_shouldConvertCorrectly() {
        testCountry(
            country = "DZ",
            expectedCountryType = NBCountryType.ALGERIA
        )
    }

    @Test
    fun country_EC_shouldConvertCorrectly() {
        testCountry(
            country = "EC",
            expectedCountryType = NBCountryType.ECUADOR
        )
    }

    @Test
    fun country_EE_shouldConvertCorrectly() {
        testCountry(
            country = "EE",
            expectedCountryType = NBCountryType.ESTONIA
        )
    }

    @Test
    fun country_EG_shouldConvertCorrectly() {
        testCountry(
            country = "EG",
            expectedCountryType = NBCountryType.EGYPT
        )
    }

    @Test
    fun country_EH_shouldConvertCorrectly() {
        testCountry(
            country = "EH",
            expectedCountryType = NBCountryType.WESTERN_SAHARA
        )
    }

    @Test
    fun country_ER_shouldConvertCorrectly() {
        testCountry(
            country = "ER",
            expectedCountryType = NBCountryType.ERITREA
        )
    }

    @Test
    fun country_ES_shouldConvertCorrectly() {
        testCountry(
            country = "ES",
            expectedCountryType = NBCountryType.SPAIN
        )
    }

    @Test
    fun country_ET_shouldConvertCorrectly() {
        testCountry(
            country = "ET",
            expectedCountryType = NBCountryType.ETHIOPIA
        )
    }

    @Test
    fun country_FI_shouldConvertCorrectly() {
        testCountry(
            country = "FI",
            expectedCountryType = NBCountryType.FINLAND
        )
    }

    @Test
    fun country_FJ_shouldConvertCorrectly() {
        testCountry(
            country = "FJ",
            expectedCountryType = NBCountryType.FIJI
        )
    }

    @Test
    fun country_FK_shouldConvertCorrectly() {
        testCountry(
            country = "FK",
            expectedCountryType = NBCountryType.FALKLAND_ISLANDS
        )
    }

    @Test
    fun country_FM_shouldConvertCorrectly() {
        testCountry(
            country = "FM",
            expectedCountryType = NBCountryType.MICRONESIA
        )
    }

    @Test
    fun country_FO_shouldConvertCorrectly() {
        testCountry(
            country = "FO",
            expectedCountryType = NBCountryType.FAROE_ISLANDS
        )
    }

    @Test
    fun country_FR_shouldConvertCorrectly() {
        testCountry(
            country = "FR",
            expectedCountryType = NBCountryType.FRANCE
        )
    }

    @Test
    fun country_GA_shouldConvertCorrectly() {
        testCountry(
            country = "GA",
            expectedCountryType = NBCountryType.GABON
        )
    }

    @Test
    fun country_GB_shouldConvertCorrectly() {
        testCountry(
            country = "GB",
            expectedCountryType = NBCountryType.UNITED_KINGDOM
        )
    }

    @Test
    fun country_GD_shouldConvertCorrectly() {
        testCountry(
            country = "GD",
            expectedCountryType = NBCountryType.GRENADA
        )
    }

    @Test
    fun country_GE_shouldConvertCorrectly() {
        testCountry(
            country = "GE",
            expectedCountryType = NBCountryType.GEORGIA
        )
    }

    @Test
    fun country_GF_shouldConvertCorrectly() {
        testCountry(
            country = "GF",
            expectedCountryType = NBCountryType.FRENCH_GUIANA
        )
    }

    @Test
    fun country_GG_shouldConvertCorrectly() {
        testCountry(
            country = "GG",
            expectedCountryType = NBCountryType.GUERNSEY
        )
    }

    @Test
    fun country_GH_shouldConvertCorrectly() {
        testCountry(
            country = "GH",
            expectedCountryType = NBCountryType.GHANA
        )
    }

    @Test
    fun country_GI_shouldConvertCorrectly() {
        testCountry(
            country = "GI",
            expectedCountryType = NBCountryType.GIBRALTAR
        )
    }

    @Test
    fun country_GL_shouldConvertCorrectly() {
        testCountry(
            country = "GL",
            expectedCountryType = NBCountryType.GREENLAND
        )
    }

    @Test
    fun country_GM_shouldConvertCorrectly() {
        testCountry(
            country = "GM",
            expectedCountryType = NBCountryType.GAMBIA
        )
    }

    @Test
    fun country_GN_shouldConvertCorrectly() {
        testCountry(
            country = "GN",
            expectedCountryType = NBCountryType.GUINEA
        )
    }

    @Test
    fun country_GP_shouldConvertCorrectly() {
        testCountry(
            country = "GP",
            expectedCountryType = NBCountryType.GUADELOUPE
        )
    }

    @Test
    fun country_GQ_shouldConvertCorrectly() {
        testCountry(
            country = "GQ",
            expectedCountryType = NBCountryType.EQUATORIAL_GUINEA
        )
    }

    @Test
    fun country_GR_shouldConvertCorrectly() {
        testCountry(
            country = "GR",
            expectedCountryType = NBCountryType.GREECE
        )
    }

    @Test
    fun country_GS_shouldConvertCorrectly() {
        testCountry(
            country = "GS",
            expectedCountryType = NBCountryType.SOUTH_GEORGIA
        )
    }

    @Test
    fun country_GT_shouldConvertCorrectly() {
        testCountry(
            country = "GT",
            expectedCountryType = NBCountryType.GUATEMALA
        )
    }

    @Test
    fun country_GU_shouldConvertCorrectly() {
        testCountry(
            country = "GU",
            expectedCountryType = NBCountryType.GUAM
        )
    }

    @Test
    fun country_GW_shouldConvertCorrectly() {
        testCountry(
            country = "GW",
            expectedCountryType = NBCountryType.GUINEA_BISSAU
        )
    }

    @Test
    fun country_GY_shouldConvertCorrectly() {
        testCountry(
            country = "GY",
            expectedCountryType = NBCountryType.GUYANA
        )
    }

    @Test
    fun country_HK_shouldConvertCorrectly() {
        testCountry(
            country = "HK",
            expectedCountryType = NBCountryType.HONG_KONG
        )
    }

    @Test
    fun country_HM_shouldConvertCorrectly() {
        testCountry(
            country = "HM",
            expectedCountryType = NBCountryType.HEARD_ISLAND_AND_MCDONALD_ISLANDS
        )
    }

    @Test
    fun country_HN_shouldConvertCorrectly() {
        testCountry(
            country = "HN",
            expectedCountryType = NBCountryType.HONDURAS
        )
    }

    @Test
    fun country_HR_shouldConvertCorrectly() {
        testCountry(
            country = "HR",
            expectedCountryType = NBCountryType.CROATIA
        )
    }

    @Test
    fun country_HT_shouldConvertCorrectly() {
        testCountry(
            country = "HT",
            expectedCountryType = NBCountryType.HAITI
        )
    }

    @Test
    fun country_HU_shouldConvertCorrectly() {
        testCountry(
            country = "HU",
            expectedCountryType = NBCountryType.HUNGARY
        )
    }

    @Test
    fun country_ID_shouldConvertCorrectly() {
        testCountry(
            country = "ID",
            expectedCountryType = NBCountryType.INDONESIA
        )
    }

    @Test
    fun country_IE_shouldConvertCorrectly() {
        testCountry(
            country = "IE",
            expectedCountryType = NBCountryType.IRELAND
        )
    }

    @Test
    fun country_IL_shouldConvertCorrectly() {
        testCountry(
            country = "IL",
            expectedCountryType = NBCountryType.ISRAEL
        )
    }

    @Test
    fun country_IM_shouldConvertCorrectly() {
        testCountry(
            country = "IM",
            expectedCountryType = NBCountryType.ISLE_OF_MAN
        )
    }

    @Test
    fun country_IN_shouldConvertCorrectly() {
        testCountry(
            country = "IN",
            expectedCountryType = NBCountryType.INDIA
        )
    }

    @Test
    fun country_IO_shouldConvertCorrectly() {
        testCountry(
            country = "IO",
            expectedCountryType = NBCountryType.BRITISH_INDIAN_OCEAN_TERRITORY
        )
    }

    @Test
    fun country_IQ_shouldConvertCorrectly() {
        testCountry(
            country = "IQ",
            expectedCountryType = NBCountryType.IRAQ
        )
    }

    @Test
    fun country_IR_shouldConvertCorrectly() {
        testCountry(
            country = "IR",
            expectedCountryType = NBCountryType.IRAN
        )
    }

    @Test
    fun country_IS_shouldConvertCorrectly() {
        testCountry(
            country = "IS",
            expectedCountryType = NBCountryType.ICELAND
        )
    }

    @Test
    fun country_IT_shouldConvertCorrectly() {
        testCountry(
            country = "IT",
            expectedCountryType = NBCountryType.ITALY
        )
    }

    @Test
    fun country_JE_shouldConvertCorrectly() {
        testCountry(
            country = "JE",
            expectedCountryType = NBCountryType.JERSEY
        )
    }

    @Test
    fun country_JM_shouldConvertCorrectly() {
        testCountry(
            country = "JM",
            expectedCountryType = NBCountryType.JAMAICA
        )
    }

    @Test
    fun country_JO_shouldConvertCorrectly() {
        testCountry(
            country = "JO",
            expectedCountryType = NBCountryType.JORDAN
        )
    }

    @Test
    fun country_JP_shouldConvertCorrectly() {
        testCountry(
            country = "JP",
            expectedCountryType = NBCountryType.JAPAN
        )
    }

    @Test
    fun country_KE_shouldConvertCorrectly() {
        testCountry(
            country = "KE",
            expectedCountryType = NBCountryType.KENYA
        )
    }

    @Test
    fun country_KG_shouldConvertCorrectly() {
        testCountry(
            country = "KG",
            expectedCountryType = NBCountryType.KYRGYZSTAN
        )
    }

    @Test
    fun country_KH_shouldConvertCorrectly() {
        testCountry(
            country = "KH",
            expectedCountryType = NBCountryType.CAMBODIA
        )
    }

    @Test
    fun country_KI_shouldConvertCorrectly() {
        testCountry(
            country = "KI",
            expectedCountryType = NBCountryType.KIRIBATI
        )
    }

    @Test
    fun country_KM_shouldConvertCorrectly() {
        testCountry(
            country = "KM",
            expectedCountryType = NBCountryType.COMOROS
        )
    }

    @Test
    fun country_KN_shouldConvertCorrectly() {
        testCountry(
            country = "KN",
            expectedCountryType = NBCountryType.SAINT_KITTS_AND_NEVIS
        )
    }

    @Test
    fun country_KP_shouldConvertCorrectly() {
        testCountry(
            country = "KP",
            expectedCountryType = NBCountryType.NORTH_KOREA
        )
    }

    @Test
    fun country_KR_shouldConvertCorrectly() {
        testCountry(
            country = "KR",
            expectedCountryType = NBCountryType.SOUTH_KOREA
        )
    }

    @Test
    fun country_KW_shouldConvertCorrectly() {
        testCountry(
            country = "KW",
            expectedCountryType = NBCountryType.KUWAIT
        )
    }

    @Test
    fun country_KY_shouldConvertCorrectly() {
        testCountry(
            country = "KY",
            expectedCountryType = NBCountryType.CAYMAN_ISLANDS
        )
    }

    @Test
    fun country_KZ_shouldConvertCorrectly() {
        testCountry(
            country = "KZ",
            expectedCountryType = NBCountryType.KAZAKHSTAN
        )
    }

    @Test
    fun country_LA_shouldConvertCorrectly() {
        testCountry(
            country = "LA",
            expectedCountryType = NBCountryType.LAOS
        )
    }

    @Test
    fun country_LB_shouldConvertCorrectly() {
        testCountry(
            country = "LB",
            expectedCountryType = NBCountryType.LEBANON
        )
    }

    @Test
    fun country_LC_shouldConvertCorrectly() {
        testCountry(
            country = "LC",
            expectedCountryType = NBCountryType.SAINT_LUCIA
        )
    }

    @Test
    fun country_LI_shouldConvertCorrectly() {
        testCountry(
            country = "LI",
            expectedCountryType = NBCountryType.LIECHTENSTEIN
        )
    }

    @Test
    fun country_LK_shouldConvertCorrectly() {
        testCountry(
            country = "LK",
            expectedCountryType = NBCountryType.SRI_LANKA
        )
    }

    @Test
    fun country_LR_shouldConvertCorrectly() {
        testCountry(
            country = "LR",
            expectedCountryType = NBCountryType.LIBERIA
        )
    }

    @Test
    fun country_LS_shouldConvertCorrectly() {
        testCountry(
            country = "LS",
            expectedCountryType = NBCountryType.LESOTHO
        )
    }

    @Test
    fun country_LT_shouldConvertCorrectly() {
        testCountry(
            country = "LT",
            expectedCountryType = NBCountryType.LITHUANIA
        )
    }

    @Test
    fun country_LU_shouldConvertCorrectly() {
        testCountry(
            country = "LU",
            expectedCountryType = NBCountryType.LUXEMBOURG
        )
    }

    @Test
    fun country_LV_shouldConvertCorrectly() {
        testCountry(
            country = "LV",
            expectedCountryType = NBCountryType.LATVIA
        )
    }

    @Test
    fun country_LY_shouldConvertCorrectly() {
        testCountry(
            country = "LY",
            expectedCountryType = NBCountryType.LIBYA
        )
    }

    @Test
    fun country_MA_shouldConvertCorrectly() {
        testCountry(
            country = "MA",
            expectedCountryType = NBCountryType.MOROCCO
        )
    }

    @Test
    fun country_MC_shouldConvertCorrectly() {
        testCountry(
            country = "MC",
            expectedCountryType = NBCountryType.MONACO
        )
    }

    @Test
    fun country_MD_shouldConvertCorrectly() {
        testCountry(
            country = "MD",
            expectedCountryType = NBCountryType.MOLDOVA
        )
    }

    @Test
    fun country_ME_shouldConvertCorrectly() {
        testCountry(
            country = "ME",
            expectedCountryType = NBCountryType.MONTENEGRO
        )
    }

    @Test
    fun country_MF_shouldConvertCorrectly() {
        testCountry(
            country = "MF",
            expectedCountryType = NBCountryType.SAINT_MARTIN
        )
    }

    @Test
    fun country_MG_shouldConvertCorrectly() {
        testCountry(
            country = "MG",
            expectedCountryType = NBCountryType.MADAGASCAR
        )
    }

    @Test
    fun country_MH_shouldConvertCorrectly() {
        testCountry(
            country = "MH",
            expectedCountryType = NBCountryType.MARSHALL_ISLANDS
        )
    }

    @Test
    fun country_MK_shouldConvertCorrectly() {
        testCountry(
            country = "MK",
            expectedCountryType = NBCountryType.NORTH_MACEDONIA
        )
    }

    @Test
    fun country_ML_shouldConvertCorrectly() {
        testCountry(
            country = "ML",
            expectedCountryType = NBCountryType.MALI
        )
    }

    @Test
    fun country_MM_shouldConvertCorrectly() {
        testCountry(
            country = "MM",
            expectedCountryType = NBCountryType.MYANMAR
        )
    }

    @Test
    fun country_MN_shouldConvertCorrectly() {
        testCountry(
            country = "MN",
            expectedCountryType = NBCountryType.MONGOLIA
        )
    }

    @Test
    fun country_MO_shouldConvertCorrectly() {
        testCountry(
            country = "MO",
            expectedCountryType = NBCountryType.MACAU
        )
    }

    @Test
    fun country_MP_shouldConvertCorrectly() {
        testCountry(
            country = "MP",
            expectedCountryType = NBCountryType.NORTHERN_MARIANA_ISLANDS
        )
    }

    @Test
    fun country_MQ_shouldConvertCorrectly() {
        testCountry(
            country = "MQ",
            expectedCountryType = NBCountryType.MARTINIQUE
        )
    }

    @Test
    fun country_MR_shouldConvertCorrectly() {
        testCountry(
            country = "MR",
            expectedCountryType = NBCountryType.MAURITANIA
        )
    }

    @Test
    fun country_MS_shouldConvertCorrectly() {
        testCountry(
            country = "MS",
            expectedCountryType = NBCountryType.MONTSERRAT
        )
    }

    @Test
    fun country_MT_shouldConvertCorrectly() {
        testCountry(
            country = "MT",
            expectedCountryType = NBCountryType.MALTA
        )
    }

    @Test
    fun country_MU_shouldConvertCorrectly() {
        testCountry(
            country = "MU",
            expectedCountryType = NBCountryType.MAURITIUS
        )
    }

    @Test
    fun country_MV_shouldConvertCorrectly() {
        testCountry(
            country = "MV",
            expectedCountryType = NBCountryType.MALDIVES
        )
    }

    @Test
    fun country_MW_shouldConvertCorrectly() {
        testCountry(
            country = "MW",
            expectedCountryType = NBCountryType.MALAWI
        )
    }

    @Test
    fun country_MX_shouldConvertCorrectly() {
        testCountry(
            country = "MX",
            expectedCountryType = NBCountryType.MEXICO
        )
    }

    @Test
    fun country_MY_shouldConvertCorrectly() {
        testCountry(
            country = "MY",
            expectedCountryType = NBCountryType.MALAYSIA
        )
    }

    @Test
    fun country_MZ_shouldConvertCorrectly() {
        testCountry(
            country = "MZ",
            expectedCountryType = NBCountryType.MOZAMBIQUE
        )
    }

    @Test
    fun country_NA_shouldConvertCorrectly() {
        testCountry(
            country = "NA",
            expectedCountryType = NBCountryType.NAMIBIA
        )
    }

    @Test
    fun country_NC_shouldConvertCorrectly() {
        testCountry(
            country = "NC",
            expectedCountryType = NBCountryType.NEW_CALEDONIA
        )
    }

    @Test
    fun country_NE_shouldConvertCorrectly() {
        testCountry(
            country = "NE",
            expectedCountryType = NBCountryType.NIGER
        )
    }

    @Test
    fun country_NF_shouldConvertCorrectly() {
        testCountry(
            country = "NF",
            expectedCountryType = NBCountryType.NORFOLK_ISLAND
        )
    }

    @Test
    fun country_NG_shouldConvertCorrectly() {
        testCountry(
            country = "NG",
            expectedCountryType = NBCountryType.NIGERIA
        )
    }

    @Test
    fun country_NI_shouldConvertCorrectly() {
        testCountry(
            country = "NI",
            expectedCountryType = NBCountryType.NICARAGUA
        )
    }

    @Test
    fun country_NL_shouldConvertCorrectly() {
        testCountry(
            country = "NL",
            expectedCountryType = NBCountryType.NETHERLANDS
        )
    }

    @Test
    fun country_NO_shouldConvertCorrectly() {
        testCountry(
            country = "NO",
            expectedCountryType = NBCountryType.NORWAY
        )
    }

    @Test
    fun country_NP_shouldConvertCorrectly() {
        testCountry(
            country = "NP",
            expectedCountryType = NBCountryType.NEPAL
        )
    }

    @Test
    fun country_NR_shouldConvertCorrectly() {
        testCountry(
            country = "NR",
            expectedCountryType = NBCountryType.NAURU
        )
    }

    @Test
    fun country_NU_shouldConvertCorrectly() {
        testCountry(
            country = "NU",
            expectedCountryType = NBCountryType.NIUE
        )
    }

    @Test
    fun country_NZ_shouldConvertCorrectly() {
        testCountry(
            country = "NZ",
            expectedCountryType = NBCountryType.NEW_ZEALAND
        )
    }

    @Test
    fun country_OM_shouldConvertCorrectly() {
        testCountry(
            country = "OM",
            expectedCountryType = NBCountryType.OMAN
        )
    }

    @Test
    fun country_PA_shouldConvertCorrectly() {
        testCountry(
            country = "PA",
            expectedCountryType = NBCountryType.PANAMA
        )
    }

    @Test
    fun country_PE_shouldConvertCorrectly() {
        testCountry(
            country = "PE",
            expectedCountryType = NBCountryType.PERU
        )
    }

    @Test
    fun country_PF_shouldConvertCorrectly() {
        testCountry(
            country = "PF",
            expectedCountryType = NBCountryType.FRENCH_POLYNESIA
        )
    }

    @Test
    fun country_PG_shouldConvertCorrectly() {
        testCountry(
            country = "PG",
            expectedCountryType = NBCountryType.PAPUA_NEW_GUINEA
        )
    }

    @Test
    fun country_PH_shouldConvertCorrectly() {
        testCountry(
            country = "PH",
            expectedCountryType = NBCountryType.PHILIPPINES
        )
    }

    @Test
    fun country_PK_shouldConvertCorrectly() {
        testCountry(
            country = "PK",
            expectedCountryType = NBCountryType.PAKISTAN
        )
    }

    @Test
    fun country_PL_shouldConvertCorrectly() {
        testCountry(
            country = "PL",
            expectedCountryType = NBCountryType.POLAND
        )
    }

    @Test
    fun country_PM_shouldConvertCorrectly() {
        testCountry(
            country = "PM",
            expectedCountryType = NBCountryType.SAINT_PIERRE_AND_MIQUELON
        )
    }

    @Test
    fun country_PN_shouldConvertCorrectly() {
        testCountry(
            country = "PN",
            expectedCountryType = NBCountryType.PITCAIRN_ISLANDS
        )
    }

    @Test
    fun country_PR_shouldConvertCorrectly() {
        testCountry(
            country = "PR",
            expectedCountryType = NBCountryType.PUERTO_RICO
        )
    }

    @Test
    fun country_PS_shouldConvertCorrectly() {
        testCountry(
            country = "PS",
            expectedCountryType = NBCountryType.PALESTINE
        )
    }

    @Test
    fun country_PT_shouldConvertCorrectly() {
        testCountry(
            country = "PT",
            expectedCountryType = NBCountryType.PORTUGAL
        )
    }

    @Test
    fun country_PW_shouldConvertCorrectly() {
        testCountry(
            country = "PW",
            expectedCountryType = NBCountryType.PALAU
        )
    }

    @Test
    fun country_PY_shouldConvertCorrectly() {
        testCountry(
            country = "PY",
            expectedCountryType = NBCountryType.PARAGUAY
        )
    }

    @Test
    fun country_QA_shouldConvertCorrectly() {
        testCountry(
            country = "QA",
            expectedCountryType = NBCountryType.QATAR
        )
    }

    @Test
    fun country_RE_shouldConvertCorrectly() {
        testCountry(
            country = "RE",
            expectedCountryType = NBCountryType.REUNION
        )
    }

    @Test
    fun country_RO_shouldConvertCorrectly() {
        testCountry(
            country = "RO",
            expectedCountryType = NBCountryType.ROMANIA
        )
    }

    @Test
    fun country_RS_shouldConvertCorrectly() {
        testCountry(
            country = "RS",
            expectedCountryType = NBCountryType.SERBIA
        )
    }

    @Test
    fun country_RU_shouldConvertCorrectly() {
        testCountry(
            country = "RU",
            expectedCountryType = NBCountryType.RUSSIA
        )
    }

    @Test
    fun country_RW_shouldConvertCorrectly() {
        testCountry(
            country = "RW",
            expectedCountryType = NBCountryType.RWANDA
        )
    }

    @Test
    fun country_SA_shouldConvertCorrectly() {
        testCountry(
            country = "SA",
            expectedCountryType = NBCountryType.SAUDI_ARABIA
        )
    }

    @Test
    fun country_SB_shouldConvertCorrectly() {
        testCountry(
            country = "SB",
            expectedCountryType = NBCountryType.SOLOMON_ISLANDS
        )
    }

    @Test
    fun country_SC_shouldConvertCorrectly() {
        testCountry(
            country = "SC",
            expectedCountryType = NBCountryType.SEYCHELLES
        )
    }

    @Test
    fun country_SD_shouldConvertCorrectly() {
        testCountry(
            country = "SD",
            expectedCountryType = NBCountryType.SUDAN
        )
    }

    @Test
    fun country_SE_shouldConvertCorrectly() {
        testCountry(
            country = "SE",
            expectedCountryType = NBCountryType.SWEDEN
        )
    }

    @Test
    fun country_SG_shouldConvertCorrectly() {
        testCountry(
            country = "SG",
            expectedCountryType = NBCountryType.SINGAPORE
        )
    }

    @Test
    fun country_SH_shouldConvertCorrectly() {
        testCountry(
            country = "SH",
            expectedCountryType = NBCountryType.SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA
        )
    }

    @Test
    fun country_SI_shouldConvertCorrectly() {
        testCountry(
            country = "SI",
            expectedCountryType = NBCountryType.SLOVENIA
        )
    }

    @Test
    fun country_SJ_shouldConvertCorrectly() {
        testCountry(
            country = "SJ",
            expectedCountryType = NBCountryType.SVALBARD_AND_JAN_MAYEN
        )
    }

    @Test
    fun country_SK_shouldConvertCorrectly() {
        testCountry(
            country = "SK",
            expectedCountryType = NBCountryType.SLOVAKIA
        )
    }

    @Test
    fun country_SL_shouldConvertCorrectly() {
        testCountry(
            country = "SL",
            expectedCountryType = NBCountryType.SIERRA_LEONE
        )
    }

    @Test
    fun country_SM_shouldConvertCorrectly() {
        testCountry(
            country = "SM",
            expectedCountryType = NBCountryType.SAN_MARINO
        )
    }

    @Test
    fun country_SN_shouldConvertCorrectly() {
        testCountry(
            country = "SN",
            expectedCountryType = NBCountryType.SENEGAL
        )
    }

    @Test
    fun country_SO_shouldConvertCorrectly() {
        testCountry(
            country = "SO",
            expectedCountryType = NBCountryType.SOMALIA
        )
    }

    @Test
    fun country_SR_shouldConvertCorrectly() {
        testCountry(
            country = "SR",
            expectedCountryType = NBCountryType.SURINAME
        )
    }

    @Test
    fun country_SS_shouldConvertCorrectly() {
        testCountry(
            country = "SS",
            expectedCountryType = NBCountryType.SOUTH_SUDAN
        )
    }

    @Test
    fun country_ST_shouldConvertCorrectly() {
        testCountry(
            country = "ST",
            expectedCountryType = NBCountryType.SAO_TOME_AND_PRINCIPE
        )
    }

    @Test
    fun country_SV_shouldConvertCorrectly() {
        testCountry(
            country = "SV",
            expectedCountryType = NBCountryType.EL_SALVADOR
        )
    }

    @Test
    fun country_SX_shouldConvertCorrectly() {
        testCountry(
            country = "SX",
            expectedCountryType = NBCountryType.SINT_MAARTEN
        )
    }

    @Test
    fun country_SY_shouldConvertCorrectly() {
        testCountry(
            country = "SY",
            expectedCountryType = NBCountryType.SYRIA
        )
    }

    @Test
    fun country_SZ_shouldConvertCorrectly() {
        testCountry(
            country = "SZ",
            expectedCountryType = NBCountryType.ESWATINI
        )
    }

    @Test
    fun country_TC_shouldConvertCorrectly() {
        testCountry(
            country = "TC",
            expectedCountryType = NBCountryType.TURKS_AND_CAICOS_ISLANDS
        )
    }

    @Test
    fun country_TD_shouldConvertCorrectly() {
        testCountry(
            country = "TD",
            expectedCountryType = NBCountryType.CHAD
        )
    }

    @Test
    fun country_TF_shouldConvertCorrectly() {
        testCountry(
            country = "TF",
            expectedCountryType = NBCountryType.FRENCH_SOUTHERN_AND_ANTARCTIC_LANDS
        )
    }

    @Test
    fun country_TG_shouldConvertCorrectly() {
        testCountry(
            country = "TG",
            expectedCountryType = NBCountryType.TOGO
        )
    }

    @Test
    fun country_TH_shouldConvertCorrectly() {
        testCountry(
            country = "TH",
            expectedCountryType = NBCountryType.THAILAND
        )
    }

    @Test
    fun country_TJ_shouldConvertCorrectly() {
        testCountry(
            country = "TJ",
            expectedCountryType = NBCountryType.TAJIKISTAN
        )
    }

    @Test
    fun country_TK_shouldConvertCorrectly() {
        testCountry(
            country = "TK",
            expectedCountryType = NBCountryType.TOKELAU
        )
    }

    @Test
    fun country_TL_shouldConvertCorrectly() {
        testCountry(
            country = "TL",
            expectedCountryType = NBCountryType.TIMOR_LESTE
        )
    }

    @Test
    fun country_TM_shouldConvertCorrectly() {
        testCountry(
            country = "TM",
            expectedCountryType = NBCountryType.TURKMENISTAN
        )
    }

    @Test
    fun country_TN_shouldConvertCorrectly() {
        testCountry(
            country = "TN",
            expectedCountryType = NBCountryType.TUNISIA
        )
    }

    @Test
    fun country_TO_shouldConvertCorrectly() {
        testCountry(
            country = "TO",
            expectedCountryType = NBCountryType.TONGA
        )
    }

    @Test
    fun country_TR_shouldConvertCorrectly() {
        testCountry(
            country = "TR",
            expectedCountryType = NBCountryType.TURKEY
        )
    }

    @Test
    fun country_TT_shouldConvertCorrectly() {
        testCountry(
            country = "TT",
            expectedCountryType = NBCountryType.TRINIDAD_AND_TOBAGO
        )
    }

    @Test
    fun country_TV_shouldConvertCorrectly() {
        testCountry(
            country = "TV",
            expectedCountryType = NBCountryType.TUVALU
        )
    }

    @Test
    fun country_TW_shouldConvertCorrectly() {
        testCountry(
            country = "TW",
            expectedCountryType = NBCountryType.TAIWAN
        )
    }

    @Test
    fun country_TZ_shouldConvertCorrectly() {
        testCountry(
            country = "TZ",
            expectedCountryType = NBCountryType.TANZANIA
        )
    }

    @Test
    fun country_UA_shouldConvertCorrectly() {
        testCountry(
            country = "UA",
            expectedCountryType = NBCountryType.UKRAINE
        )
    }

    @Test
    fun country_UG_shouldConvertCorrectly() {
        testCountry(
            country = "UG",
            expectedCountryType = NBCountryType.UGANDA
        )
    }

    @Test
    fun country_UM_shouldConvertCorrectly() {
        testCountry(
            country = "UM",
            expectedCountryType = NBCountryType.UNITED_STATES_MINOR_OUTLYING_ISLANDS
        )
    }

    @Test
    fun country_US_shouldConvertCorrectly() {
        testCountry(
            country = "US",
            expectedCountryType = NBCountryType.UNITED_STATES
        )
    }

    @Test
    fun country_UY_shouldConvertCorrectly() {
        testCountry(
            country = "UY",
            expectedCountryType = NBCountryType.URUGUAY
        )
    }

    @Test
    fun country_UZ_shouldConvertCorrectly() {
        testCountry(
            country = "UZ",
            expectedCountryType = NBCountryType.UZBEKISTAN
        )
    }

    @Test
    fun country_VA_shouldConvertCorrectly() {
        testCountry(
            country = "VA",
            expectedCountryType = NBCountryType.VATICAN_CITY
        )
    }

    @Test
    fun country_VC_shouldConvertCorrectly() {
        testCountry(
            country = "VC",
            expectedCountryType = NBCountryType.SAINT_VINCENT_AND_THE_GRENADINES
        )
    }

    @Test
    fun country_VE_shouldConvertCorrectly() {
        testCountry(
            country = "VE",
            expectedCountryType = NBCountryType.VENEZUELA
        )
    }

    @Test
    fun country_VG_shouldConvertCorrectly() {
        testCountry(
            country = "VG",
            expectedCountryType = NBCountryType.BRITISH_VIRGIN_ISLANDS
        )
    }

    @Test
    fun country_VI_shouldConvertCorrectly() {
        testCountry(
            country = "VI",
            expectedCountryType = NBCountryType.UNITED_STATES_VIRGIN_ISLANDS
        )
    }

    @Test
    fun country_VN_shouldConvertCorrectly() {
        testCountry(
            country = "VN",
            expectedCountryType = NBCountryType.VIETNAM
        )
    }

    @Test
    fun country_VU_shouldConvertCorrectly() {
        testCountry(
            country = "VU",
            expectedCountryType = NBCountryType.VANUATU
        )
    }

    @Test
    fun country_WF_shouldConvertCorrectly() {
        testCountry(
            country = "WF",
            expectedCountryType = NBCountryType.WALLIS_AND_FUTUNA
        )
    }

    @Test
    fun country_WS_shouldConvertCorrectly() {
        testCountry(
            country = "WS",
            expectedCountryType = NBCountryType.SAMOA
        )
    }

    @Test
    fun country_XK_shouldConvertCorrectly() {
        testCountry(
            country = "XK",
            expectedCountryType = NBCountryType.KOSOVO
        )
    }

    @Test
    fun country_YE_shouldConvertCorrectly() {
        testCountry(
            country = "YE",
            expectedCountryType = NBCountryType.YEMEN
        )
    }

    @Test
    fun country_YT_shouldConvertCorrectly() {
        testCountry(
            country = "YT",
            expectedCountryType = NBCountryType.MAYOTTE
        )
    }

    @Test
    fun country_ZA_shouldConvertCorrectly() {
        testCountry(
            country = "ZA",
            expectedCountryType = NBCountryType.SOUTH_AFRICA
        )
    }

    @Test
    fun country_ZM_shouldConvertCorrectly() {
        testCountry(
            country = "ZM",
            expectedCountryType = NBCountryType.ZAMBIA
        )
    }

    @Test
    fun country_ZW_shouldConvertCorrectly() {
        testCountry(
            country = "ZW",
            expectedCountryType = NBCountryType.ZIMBABWE
        )
    }

    @Test
    fun country_null_shouldBeNull() {
        testCountry(
            country = null,
            expectedCountryType = null
        )
    }

    @Test
    fun country_undefined_shouldBeNull() {
        testCountry(
            country = "undefined",
            expectedCountryType = null
        )
    }
    
    private fun testCountry(
        country: String?,
        expectedCountryType: NBCountryType?
    ) {
        // Arrange + Act
        val countryType = NBCountryType.from(country)

        // Assert
        assertEquals(expectedCountryType, countryType)
    }

}
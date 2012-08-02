using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Reflection;

namespace BabelsPrinter
{
    public class Field
    {
        public Field(string val){value = val;}
        public string value { get; set; }
    }

    public class ParametroConfiguracion
    {
        public static Field IMPRESION_CAMBIO = new Field("4");
        public static Field IMPRESION_LEYENDAS = new Field("5");
        public static Field CORTE_PAPEL = new Field("6");
        public static Field IMPRESION_MARCO = new Field("7");
        public static Field REIMPRESION_CANCELADOS = new Field("8");
        public static Field COPIAS_DOCUMENTOS = new Field("9");
        public static Field PAGO_SALDO = new Field(":");
        public static Field SONIDO_AVISO = new Field(";");
        public static Field ALTO_HOJA = new Field("<");
        public static Field ANCHO_HOJA = new Field("=");
        public static Field ESTACION_REPORTES_XZ = new Field(">");
        public static Field MODO_IMPRESION = new Field("?");
        public static Field CHEQUEO_DESBORDE = new Field("@");
        public static Field CHEQUEO_TAPA_ABIERTA = new Field("A");
    }

    public class NumerosDeCopias
    {
        public static Field NO_COPIAS = new Field("0");
        public static Field ORIGINAL = new Field("1");
        public static Field DUPLICADO = new Field("2");
        public static Field TRIPLICADO = new Field("3");
        public static Field CUADRUPLICADO = new Field("4");
    }

    public class TiposDeCorteDePapel
	{
        public static Field CORTE_TOTAL = new Field("F");
        public static Field CORTE_PARCIAL = new Field("P");
        public static Field NO_CORTE = new Field("N");
	}

    public class TiposDeAltoHoja
	{
        public static Field ALTO_REDUCIDO = new Field("M");
        public static Field ALTO_A4 = new Field("A");
        public static Field ALTO_OFICIO = new Field("O");
        public static Field ALTO_CARTA = new Field("C");
	}

    public class TiposDeAnchoHoja
	{
        public static Field ANCHO_REDUCIDO = new Field("M");
        public static Field ANCHO_NORMAL = new Field("N");
	}

    public class TiposDeEstacion
	{
        public static Field ESTACION_TICKET = new Field("T");
        public static Field ESTACION_SLIP = new Field("S");
	}

    public class TiposDeModoImpresion
	{
        public static Field USO_ESTACION_TICKET = new Field("M");
        public static Field NO_USO_ESTACION_TICKET = new Field("A");
	}

    public class DocumentosFiscales
	{
        public static Field TICKET_FACTURA_A = new Field("A");
        public static Field TICKET_FACTURA_B = new Field("B");
        public static Field FACTURA_A = new Field("0");
        public static Field FACTURA_B = new Field("1");
        public static Field TICKET_NOTA_DEBITO_A = new Field("2");
        public static Field TICKET_NOTA_DEBITO_B = new Field("3");
        public static Field NOTA_DEBITO_A = new Field("D");
        public static Field NOTA_DEBITO_B = new Field("E");
        public static Field RECIBO_A = new Field("a");
        public static Field RECIBO_B = new Field("b");
        public static Field TICKET_C = new Field("T");
	}

    public class TiposDeDocumentoCliente
    {
        public static Field TIPO_CUIT = new Field("C");
        public static Field TIPO_CUIL = new Field("L");
        public static Field TIPO_LE = new Field("0");
        public static Field TIPO_LC = new Field("1");
        public static Field TIPO_DNI = new Field("2");
        public static Field TIPO_PASAPORTE = new Field("3");
        public static Field TIPO_CI = new Field("4");
        public static Field TIPO_NINGUNO = new Field(" ");

        public static Field FromValue(string value)
        {
            if(value =="C") return TIPO_CUIT;
            if (value == "L") return TIPO_CUIL;
            if (value == "0") return TIPO_LE;
            if (value == "1") return TIPO_LC;
            if (value == "2") return TIPO_DNI;
            if (value == "3") return TIPO_PASAPORTE;
            if (value == "4") return TIPO_CI;
            if (value == " ") return TIPO_NINGUNO;
            return null;
        }
    }

    public class TiposDeResponsabilidadesCliente
    {
        public static Field RESPONSABLE_INSCRIPTO = new Field("I");
        public static Field RESPONSABLE_NO_INSCRIPTO = new Field("N");
        public static Field RESPONSABLE_EXENTO = new Field("E");
        public static Field NO_RESPONSABLE = new Field("A");
        public static Field CONSUMIDOR_FINAL = new Field("C");
        public static Field BIENES_DE_USO = new Field("B");
        public static Field NO_CATEGORIZADO = new Field("T");
        public static Field MONOTRIBUTO = new Field("M");
        public static Field MONOTRIBUTO_SOCIAL = new Field("S");
        public static Field EVENTUAL = new Field("V");
        public static Field EVENTUAL_SOCIAL = new Field("W");

        public static Field FromValue(string value)
        {
            if (value == "I") return RESPONSABLE_INSCRIPTO;
            if (value == "N") return RESPONSABLE_NO_INSCRIPTO;
            if (value == "E") return RESPONSABLE_EXENTO;
            if (value == "A") return NO_RESPONSABLE;
            if (value == "C") return CONSUMIDOR_FINAL;
            if (value == "B") return BIENES_DE_USO;
            if (value == "T") return NO_CATEGORIZADO;
            if (value == "M") return MONOTRIBUTO;
            if (value == "S") return MONOTRIBUTO_SOCIAL;
            if (value == "V") return EVENTUAL;
            if (value == "W") return EVENTUAL_SOCIAL;
            return null;
        }
    }
}

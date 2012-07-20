using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BabelsPrinter
{
    class HasarTypes
    {
        private Dictionary<ParametroConfiguracion, string> ParamtroConfiguracionD = new Dictionary<ParametroConfiguracion, string>();
        public enum ParametroConfiguracion
        {
            IMPRESION_CAMBIO = '4',
            IMPRESION_LEYENDAS = '5',
            CORTE_PAPEL = '6',
            IMPRESION_MARCO = '7',
            REIMPRESION_CANCELADOS = '8',
            COPIAS_DOCUMENTOS = '9',
            PAGO_SALDO = ':',
            SONIDO_AVISO = ';',
            ALTO_HOJA = '<',
            ANCHO_HOJA = '=',
            ESTACION_REPORTES_XZ = '>',
            MODO_IMPRESION = '?',
            CHEQUEO_DESBORDE = '@',
            CHEQUEO_TAPA_ABIERTA = 'A'
        }

        public HasarTypes(){
            ParamtroConfiguracionD.Add(ParametroConfiguracion.IMPRESION_CAMBIO, "4");
            ParamtroConfiguracionD.Add(ParametroConfiguracion.IMPRESION_LEYENDAS, "5");
            ParamtroConfiguracionD.Add(ParametroConfiguracion.CORTE_PAPEL, "6");
            ParamtroConfiguracionD.Add(ParametroConfiguracion.IMPRESION_MARCO, "7");
            ParamtroConfiguracionD.Add(ParametroConfiguracion.REIMPRESION_CANCELADOS, "8");
            ParamtroConfiguracionD.Add(ParametroConfiguracion.COPIAS_DOCUMENTOS, "9");
            ParamtroConfiguracionD.Add(ParametroConfiguracion.PAGO_SALDO, ":");
            ParamtroConfiguracionD.Add(ParametroConfiguracion.SONIDO_AVISO, ";");
            ParamtroConfiguracionD.Add(ParametroConfiguracion.ALTO_HOJA, "<");
            ParamtroConfiguracionD.Add(ParametroConfiguracion.ANCHO_HOJA, "=");
            ParamtroConfiguracionD.Add(ParametroConfiguracion.ESTACION_REPORTES_XZ, ">");
            ParamtroConfiguracionD.Add(ParametroConfiguracion.MODO_IMPRESION, "?");
            ParamtroConfiguracionD.Add(ParametroConfiguracion.CHEQUEO_DESBORDE, "@");
            ParamtroConfiguracionD.Add(ParametroConfiguracion.CHEQUEO_TAPA_ABIERTA, "A");
        }

        public string GetValue(ParametroConfiguracion param)
        {
            return ParamtroConfiguracionD[param];
        }
    }
}

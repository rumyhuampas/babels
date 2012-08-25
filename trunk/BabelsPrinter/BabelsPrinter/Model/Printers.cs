using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BabelsPrinter.Properties;

namespace BabelsPrinter
{
    public static class Printers
    {
        public const string PRINTER_FISCAL = "FISCAL";
        public const string PRINTER_NOFISCAL = "NOFISCAL";
        public const string PRINTER_COCINA = "COCINA";
        public const string PRINTER_X = "X";

        public static string GetPrinterList(){
            string[] printerList = Settings.Default.Printers.Split(';');
            string result = "";
            foreach (string printer in printerList)
            {
                result += "'" + printer + "',";
            }
            result = result.Substring(0, result.Length - 1);
            return result;
        }
    }
}

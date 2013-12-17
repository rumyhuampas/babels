using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MySQLDriverCS;
using System.Drawing.Printing;
using System.Drawing;

namespace BabelsPrinter.Helpers
{
    public class ClientPrintHelper: XPrintHelper
    {
        public ClientPrintHelper(PrintPageEventArgs printer):base(printer)
        {
        }

        public override void DrawJobItems(PrintJob job)
        {
            throw new Exception("Method is not implemented");
        }

        public override void DrawJobTotal(PrintJob job)
        {
            throw new Exception("Method is not implemented");
        }
    }
}

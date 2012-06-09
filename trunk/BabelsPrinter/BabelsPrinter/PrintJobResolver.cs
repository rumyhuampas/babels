using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.InteropServices;

namespace BabelsPrinter
{
    class PrintJobResolver
    {
        [DllImport("Hasar715.dll")]
        public static extern void InitPrinter();
        [DllImport("Hasar715.dll")]
        public static extern int GetNum();

        public PrintJobResolver()
        {
            InitPrinter();
            int i = GetNum();
        }

        public void ProcessJob(PrintJob job)
        {

        }
    }
}

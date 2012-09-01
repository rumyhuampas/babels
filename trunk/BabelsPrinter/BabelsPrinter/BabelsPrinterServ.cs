using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Linq;
using System.ServiceProcess;
using System.Text;

namespace BabelsPrinter
{
    public partial class BabelsPrinterServ : ServiceBase
    {
        private PrinterService printer;

        public BabelsPrinterServ()
        {
            InitializeComponent();
        }

        protected override void OnStart(string[] args)
        {
            printer = new PrinterService();
            printer.Start();
        }

        protected override void OnStop()
        {
            printer.watcher.StopWatching();
        }
    }
}

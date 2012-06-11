using System.Diagnostics;
using System.ServiceProcess;
using System.Threading;

namespace BabelsPrinter
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        static void Main()
        {
            if (!Debugger.IsAttached)
            {
                ServiceBase[] ServicesToRun;
                ServicesToRun = new ServiceBase[] { new BabelsPrinterServ() };
                ServiceBase.Run(ServicesToRun);
            }
            else
            {
                Printer printer = new Printer();
                printer.Start();
                //PrintJobResolver pjr = new PrintJobResolver();
                Thread.Sleep(Timeout.Infinite);
            }
        }
    }
}

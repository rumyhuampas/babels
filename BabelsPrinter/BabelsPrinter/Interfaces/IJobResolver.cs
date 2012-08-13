using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BabelsPrinter.Interfaces
{
    interface IJobResolver
    {
        void ProcessJob(PrintJob job);
    }
}

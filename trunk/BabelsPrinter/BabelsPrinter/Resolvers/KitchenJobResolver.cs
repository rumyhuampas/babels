using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using BabelsPrinter.Properties;
using BabelsPrinter.Interfaces;

namespace BabelsPrinter
{
    public class KitchenJobResolver : IJobResolver
    {
        public KitchenJobResolver()
        {
            
        }

        public void ProcessJob(PrintJob job)
        {
            Logger.Log(Logger.MT_INFO, "Processing kitchen job: " + job.Id.ToString(), Settings.Default.LogLevel >= 4);
            try
            {
                switch (job.Move.Type.Name)
                {
                    case Movement.MT_VENTAA:
                        
                        break;
                    case Movement.MT_VENTAB:
                        
                        break;
                    case Movement.MT_CANCELACION:
                        break;
                    case Movement.MT_APERTURA:
                        break;
                    case Movement.MT_CIERRE:
                        break;
                    case Movement.MT_CIERREPARCIAL:
                        break;
                    case Movement.MT_DEPOSITO:
                        break;
                    case Movement.MT_EXTRACCION:
                        break;
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}

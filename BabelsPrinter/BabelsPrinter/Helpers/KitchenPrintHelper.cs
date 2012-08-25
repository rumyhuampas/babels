using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;
using System.Drawing.Printing;
using BabelsPrinter.Properties;
using MySQLDriverCS;

namespace BabelsPrinter.Model
{
    public class KitchenPrintHelper
    {
        private MySQLConnection Conn;
        private PrintPageEventArgs Printer;
        private Rectangle RecLogo;
        private Rectangle RecTitle;
        private Rectangle RecInfo;
        private int LeftMargin;
        private int TopMargin;
        private int PageWidth;
        private int PageHeight;

        public KitchenPrintHelper(PrintPageEventArgs printer)
        {
            Printer = printer;
            LeftMargin = Printer.MarginBounds.Left;
            TopMargin = Printer.MarginBounds.Top;
            PageWidth = Printer.MarginBounds.Width;
            PageHeight = Printer.MarginBounds.Height;
            RecLogo = new Rectangle(LeftMargin, TopMargin, 100, 100);
            Conn = PrinterService.GetDBConn();
        }

        public void DrawLogo()
        {
            Printer.Graphics.DrawImage(Resources.LOGO_BABELS, RecLogo);
        }

        public void DrawTitle(string title)
        {
            Font fontTitle = new Font("Calibri", 18, FontStyle.Bold);
            SizeF sizeTitle = Printer.Graphics.MeasureString(title, fontTitle);
            RecTitle = new Rectangle(LeftMargin + RecLogo.Width + 10, TopMargin + (RecLogo.Height / 2), (int)Math.Ceiling(sizeTitle.Width), (int)Math.Ceiling(sizeTitle.Height));
            Printer.Graphics.DrawString(title, fontTitle, Brushes.Black, RecTitle);
        }

        public void DrawJobInfo(PrintJob job)
        {
            Font fontInfo = new Font("Calibri", 11, FontStyle.Regular);
            RecInfo = new Rectangle(LeftMargin, RecLogo.Location.Y + RecLogo.Height + 5, PageWidth, 20);
            string jobInfo = "ID: " + job.Move.Id.ToString() + " - Fecha pedido: " + job.Move.DatePosted.ToString();
            Printer.Graphics.DrawString(jobInfo, fontInfo, Brushes.Black, RecInfo);
        }

        public void DrawJobItems(PrintJob job)
        {
            Font fontInfo = new Font("Calibri", 11, FontStyle.Regular);
            RecInfo = new Rectangle(LeftMargin, RecInfo.Location.Y + RecInfo.Height + 5, PageWidth, 20);
            int i = 1;
            string jobInfo = "";
            string kitchenID = job.Printer.Substring(job.Printer.IndexOf("_") + 1, job.Printer.Length - (job.Printer.IndexOf("_") + 1));
            foreach (SaleItem item in job.Move.Items.items)
            {
                if (item.Type == "PRODUCT")
                {
                    Product prod = new Product(this.Conn);
                    prod.Load(item.Id);
                    if (prod.IdKitchen.ToString() == kitchenID)
                    {
                        jobInfo = i.ToString() + " - Producto: " + prod.Name + " - Cantidad:" + item.Amount;
                        RecInfo.Y = RecInfo.Location.Y + RecInfo.Height;
                        Printer.Graphics.DrawString(jobInfo, fontInfo, Brushes.Black, RecInfo);
                        i++;
                    }
                }
                else
                {
                    Combo combo = new Combo(this.Conn);
                    combo.Load(item.Id);
                    foreach (Product prod in combo.Products)
                    {
                        if (prod.IdKitchen.ToString() == kitchenID)
                        {
                            jobInfo = i.ToString() + " - [Combo: " + combo.Name + "] Producto: " + prod.Name + " - Cantidad:" + item.Amount;
                            RecInfo.Y = RecInfo.Location.Y + RecInfo.Height;
                            Printer.Graphics.DrawString(jobInfo, fontInfo, Brushes.Black, RecInfo);
                            i++;
                        }
                    }
                }
            }
        }
    }
}

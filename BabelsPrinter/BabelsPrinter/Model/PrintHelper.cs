using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;
using System.Drawing.Printing;
using BabelsPrinter.Properties;

namespace BabelsPrinter.Model
{
    public class KitchenPrintHelper
    {
        private PrintPageEventArgs Printer;
        private Rectangle RecLogo;
        private Rectangle RecTitle;
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
            RecTitle = new Rectangle(LeftMargin, TopMargin + RecLogo.Height + 5, PageWidth, 20);
            string jobInfo = "ID: " + job.Move.Id.ToString() + " - Fecha pedido: " + job.Move.DatePosted.ToString();
            Printer.Graphics.DrawString(jobInfo, fontInfo, Brushes.Black, RecTitle);
        }

        public void DrawJobItems(PrintJob job)
        {
            Font fontInfo = new Font("Calibri", 11, FontStyle.Regular);
            RecTitle = new Rectangle(LeftMargin, TopMargin + RecLogo.Height + 5, PageWidth, 20);
            int i = 1;
            string jobInfo = "";
            foreach (SaleItem item in job.Move.Items.items)
            {
                jobInfo = i.ToString() + " - Nombre: " + item.Name + " - Tipo: " + item.Type + " - Cantidad:" + item.Amount;
                RecTitle.Y = TopMargin + RecLogo.Height + (i * 20);
                Printer.Graphics.DrawString(jobInfo, fontInfo, Brushes.Black, RecTitle);
                i++;
            }
        }
    }
}

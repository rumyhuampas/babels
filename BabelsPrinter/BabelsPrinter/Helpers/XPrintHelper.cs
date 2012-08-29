using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MySQLDriverCS;
using System.Drawing.Printing;
using System.Drawing;
using BabelsPrinter.Properties;
using BabelsPrinter.Model;

namespace BabelsPrinter.Helpers
{
    public class XPrintHelper
    {
        private const string LINE = "----------------------------------";
        private MySQLConnection Conn;
        private PrintPageEventArgs Printer;
        private Rectangle Rec;
        private Font TextFont;
        private Font TextFontBold;
        private int LeftMargin;
        private int TopMargin;
        private int PageWidth;
        private int PageHeight;

        public XPrintHelper(PrintPageEventArgs printer)
        {
            Printer = printer;
            LeftMargin = Printer.MarginBounds.Left;
            TopMargin = Printer.MarginBounds.Top;
            TextFont = new Font("Courier New", 10, FontStyle.Regular);
            TextFontBold = new Font("Courier New", 10, FontStyle.Bold);
            SizeF sizeTitle = Printer.Graphics.MeasureString(LINE, TextFont);
            PageWidth = (int)sizeTitle.Width;
            PageHeight = Printer.MarginBounds.Height;
            Rec = new Rectangle(LeftMargin, TopMargin, PageWidth, 20);
            Conn = PrinterService.GetDBConn();
        }

        public void DrawNoFiscal()
        {
            string text = "**** NO FISCAL     NO FISCAL ****";
            DrawText(text);
        }

        public void DrawLine()
        {
            DrawText(LINE);
        }

        public void AddWhiteSpace()
        {
            Rec.Y += 20;
        }

        public void DrawText(string text)
        {
            Printer.Graphics.DrawString(text, TextFont, Brushes.Black, Rec);
            Rec.Y += 20;
        }

        public void DrawJobItems(PrintJob job)
        {
            Rectangle auxRec = new Rectangle(Rec.X, Rec.Y, (Rec.Width / 3) * 2, Rec.Height);
            Rectangle auxRecLast = new Rectangle(auxRec.X + auxRec.Width, auxRec.Y, Rec.Width / 3, Rec.Height);
            foreach (SaleItem item in job.Move.Items.items)
            {
                Printer.Graphics.DrawString(item.Amount.ToString() + "/" + item.Price.ToString(), TextFont, Brushes.Black, auxRec);
                Printer.Graphics.DrawString("(" + item.IVA.ToString() + ")", TextFont, Brushes.Black, auxRecLast);
                Rec.Y += 20;
                auxRec.Y += 20;
                auxRecLast.Y += 20;
                Printer.Graphics.DrawString(item.Name, TextFont, Brushes.Black, auxRec);
                Printer.Graphics.DrawString(Convert.ToString(item.Amount * item.Price), TextFont, Brushes.Black, auxRecLast, new StringFormat(StringFormatFlags.DirectionRightToLeft));
                Rec.Y += 20;
                auxRec.Y += 20;
                auxRecLast.Y += 20;
            }
        }

        public void DrawJobTotal(PrintJob job)
        {
            Rectangle auxRec = new Rectangle(Rec.X, Rec.Y, (Rec.Width / 3) * 2, Rec.Height);
            Rectangle auxRecLast = new Rectangle(auxRec.X + auxRec.Width, auxRec.Y, Rec.Width / 3, Rec.Height);

            Printer.Graphics.DrawString("TOTAL", TextFontBold, Brushes.Black, auxRec);
            Printer.Graphics.DrawString("$" + job.Move.Amount.ToString(), TextFontBold, Brushes.Black, auxRecLast, new StringFormat(StringFormatFlags.DirectionRightToLeft));
            Rec.Y += 20;
            auxRec.Y += 20;
            auxRecLast.Y += 20;
        }
    }
}

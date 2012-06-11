using System.Runtime.InteropServices;

namespace BabelsPrinter
{
    public static class Hasar715
    {
        [DllImport("Hasar715.dll")]
        public static extern void InitPrinter();
        [DllImport("Hasar715.dll")]
        public static extern int GetNum();
    }
}

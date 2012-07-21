using System.Runtime.InteropServices;
using System.Text;
using System;

namespace BabelsPrinter
{
    public static class Hasar715
    {
        public static unsafe sbyte* ToSbyte(string str)
        {
            byte[] bytes = Encoding.ASCII.GetBytes(str);
            sbyte* sp;
            fixed (byte* p = bytes)
            {
                sp = (sbyte*)p;
            }
            return sp;
        }
    }
}

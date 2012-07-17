#include "Logger.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <ctime>
#include <stdarg.h>

using namespace std;

Logger::Logger(char *fPath): filePath(fPath) {}

std::string Logger::currentDateTime() {
    time_t     now = time(0);
    struct tm  tstruct;
    char       buf[80];
    tstruct = *localtime(&now);
    strftime(buf, sizeof(buf), "%Y-%m-%d.%X", &tstruct);

    return buf;
}

void Logger::Log(const std::string &Message){
	ofstream myfile;

	myfile.open (filePath, ios::app);
	myfile << currentDateTime() + ": " + Message + "\n";
	myfile.close();
}

string Logger::IntToStr(int num){
	std::stringstream out;
	out << num;
	return out.str();
}

void Logger::Log(int Message){
	Log(IntToStr(Message));
}

void Logger::Log(Excepcion &e){
	std::stringstream ss;
	ss << "Excepción: " << e.Descripcion.c_str() << " (" << e.Contexto.c_str() << ")\n";
	std::string s = ss.str();
	Log(s);
}

/*void Logger::Logf(const char* fmt, ...){
	FILE * pFile = fopen (filePath, "w");

	va_list args;
    va_start(args,fmt);
    fprintf (pFile, fmt, args);
    va_end(args);

	fclose (pFile);
}*/

void Logger::Logf(const char* fmt, ...){
	int n, size=100;
	std::string str;
	va_list ap;
	while (1) {
		str.resize(size);
		va_start(ap, fmt);
		int n = vsnprintf((char *)str.c_str(), size, fmt, ap);
		va_end(ap);
		if (n > -1 && n < size){
			Log(str);
			break;
		}
		if (n > -1)
			size=n+1;
		else
			size*=2;
	}
}
#ifndef LOGGER_H
#define LOGGER_H

#include <string>
#include "Excepcion.h"

using namespace std;

class Logger
{
public:
	Logger(char* fPath); 
	void Log(const std::string &Message);
	void Log(Excepcion &e);
	void Log(int Message);
	void Logf(const std::string &fmt, ...);
	string static IntToStr(int num);
private:
	char *filePath;
	std::string currentDateTime();
};
#endif//LOGGER_H
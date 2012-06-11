#include "Hasar715.h"
#include "P715_403.h"

#include <iostream>
#include <fstream>

class Logger{
public:

	Logger(std::string fPath): filePath(fPath) {}

	void Log(const std::string &Message){
		/*ofstream myfile;
		myfile.open ("example.txt");
		myfile << "Writing this to a file.\n";
		myfile.close();*/
	}

private:
	std::string &filePath;
};

class Eventos : public ManejadorDeEventos
{
public:

	Eventos(ImpresorFiscal *p) : pFiscal(p) {}

	void Evento(unsigned Tipo, unsigned SubTipo)
	{
		printf ("Evento Tipo %2u SubTipo %2u <%s>\n", Tipo, SubTipo,
			pFiscal->DescripcionDeEvento((ImpresorFiscal::TiposDeEvento)Tipo, SubTipo).c_str());
	}

private:
	ImpresorFiscal *pFiscal;
};

__declspec(dllexport) void InitPrinter(const std::string &LogFilePath){
	try{
		// Instanciamos el Impresor Fiscal (en este caso 715_403)
		ImpresorFiscal *pIF = new ImpresorFiscalP715_403;
		// Estaleciendo manejador y capturador de eventos
		Eventos E (pIF);
		pIF->EstablecerManejadorDeEventos (&E);

		// Estableciendo tipo de Transporte
		pIF->EstablecerPuertoSerie (1, 9600);
	}
	catch(Excepcion &e)
	{
		printf("Excepción: %s (%s)\n", e.Descripcion.c_str(), e.Contexto.c_str());
	}
}

__declspec(dllexport) int GetNum(){
	return 1;
}
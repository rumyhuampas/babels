#include "Hasar715.h"
#include "P715_403.h"

__declspec(dllexport) void InitPrinter(){
	try{
		// Instanciamos el Impresor Fiscal (en este caso 262_100)
		ImpresorFiscal *pIF = new ImpresorFiscalP715_403;
	}
	catch(Excepcion &e)
	{
		printf("Excepción: %s (%s)\n", e.Descripcion.c_str(), e.Contexto.c_str());
	}
}

__declspec(dllexport) int GetNum(){
	return 1;
}
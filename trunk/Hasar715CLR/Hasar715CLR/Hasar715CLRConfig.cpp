#include "Hasar715CLRConfig.h"
#include "EventosHasar715.h"

Hasar715CLRConfig::Hasar715CLRConfig(ImpresorFiscal *imp, Logger *log): impresor(imp), logger(log){ }

void Hasar715CLRConfig::EstablecerPuertoSerie(int puerto){
	impresor->EstablecerPuertoSerie (puerto, 9600);
}

void Hasar715CLRConfig::EstablecerManejadorDeEventos(){
	logger->Log("Estableciendo manejador y capturador de eventos");
	EventosHasar715 *E = new EventosHasar715(impresor, logger);
	impresor->EstablecerManejadorDeEventos (E);
}

void Hasar715CLRConfig::EstablecerInterlineadoDeImpresion(int value){
	impresor->EstablecerInterlineado (value);
	logger -> Logf ("INTERLINEADO: %d", impresor->ObtenerInterlineado ());
}

void Hasar715CLRConfig::EstadoInterno(){
	// Estado Interno del Impresor
	ImpresorFiscal::RTA_EstadoInternoImpresor R;
			
	impresor->EstadoInternoImpresor (&R);
	logger -> Log ("ESTADO INTERNO IMPRESOR (PL23F");
	logger -> Logf ("Estado:      %d", R.Estado);
	logger -> Logf ("Descripción: %s", R.Descripcion.c_str ());
}
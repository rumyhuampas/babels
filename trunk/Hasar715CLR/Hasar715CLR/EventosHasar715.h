#ifndef EVENTOSHASAR715_H
#define EVENTOSHASAR715_H

#include "ImpresorFiscal.h"
#include "Eventos.h"
#include "Logger.h"

class EventosHasar715 : public ManejadorDeEventos
{
public:
	EventosHasar715::EventosHasar715(ImpresorFiscal *imp, Logger *log);
	void EventosHasar715::Evento(unsigned Tipo, unsigned SubTipo);
private:
	ImpresorFiscal *impresor;
	Logger *logger;
};

#endif//EVENTOSHASAR715_H
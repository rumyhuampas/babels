#include "EventosHasar715.h"

EventosHasar715::EventosHasar715(ImpresorFiscal *imp, Logger *log) : impresor(imp), logger(log) {}

void EventosHasar715::Evento(unsigned Tipo, unsigned SubTipo)
{
	logger->Log(impresor->DescripcionDeEvento((ImpresorFiscal::TiposDeEvento)Tipo, SubTipo));
}

#pragma once

#include "P715_403.h"

#include "IniReader.h"
#include "Logger.h"
#include "EventosHasar715.h"

#include <sstream>

using namespace System;

namespace Hasar715CLR {
	public ref class Hasar715CLR
	{
	public:
		Hasar715CLR(char *LogPath){
			logger = new Logger(LogPath);
		}

		void InitPrinter(char *IniPath){
			try{
				logger->Log("---------- Iniciando Impresor ------");

				// Instanciamos el Impresor Fiscal (en este caso 715_403)
				impresor = new ImpresorFiscalP715_403;

				logger->Log("Estableciendo manejador y capturador de eventos");
				EstablecerManejadorDeEventos();

				logger->Log("Configurando Impresor. " + string(IniPath));
				ConfigurarImpresor(IniPath);
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void ImprimirReporteZ(){
			try{
				logger->Log ("Imprimiendo Reporte Z");
				ImpresorFiscal::RTA_ReporteZX R;
			
				impresor->ReporteZ (&R);
					
				logger->Logf ("Reporte Z Nº %u\n",								R.NumeroReporte					);
				logger->Logf ("\tCantidadDFCancelados             = %u\n",		R.CantidadDFCancelados			);
				logger->Logf ("\tCantidadDNFHEmitidos             = %u\n",		R.CantidadDNFHEmitidos			);
				logger->Logf ("\tCantidadDNFEmitidos              = %u\n",		R.CantidadDNFEmitidos			);
				logger->Logf ("\tCantidadDFEmitidos               = %u\n",		R.CantidadDFEmitidos			);
				logger->Logf ("\tUltimoDocFiscalBC                = %lu\n",		R.UltimoDocFiscalBC				);
				logger->Logf ("\tUltimoDocFiscalA                 = %lu\n",		R.UltimoDocFiscalA				);
				logger->Logf ("\tMontoVentasDocFiscal             = %.2f\n",	R.MontoVentasDocFiscal			);
				logger->Logf ("\tMontoIVADocFiscal                = %.2f\n",	R.MontoIVADocFiscal				);
				logger->Logf ("\tMontoImpInternosDocFiscal        = %.2f\n",	R.MontoImpInternosDocFiscal		);
				logger->Logf ("\tMontoPercepcionesDocFiscal       = %.2f\n",	R.MontoPercepcionesDocFiscal	);
				logger->Logf ("\tMontoIVANoInscriptoDocFiscal     = %.2f\n",	R.MontoIVANoInscriptoDocFiscal	);
				logger->Logf ("\tUltimaNotaCreditoBC              = %lu\n",		R.UltimaNotaCreditoBC			);
				logger->Logf ("\tUltimaNotaCreditoA               = %lu\n",		R.UltimaNotaCreditoA			);
				logger->Logf ("\tMontoVentasNotaCredito           = %.2f\n",	R.MontoVentasNotaCredito		);
				logger->Logf ("\tMontoIVANotaCredito              = %.2f\n",	R.MontoIVANotaCredito			);
				logger->Logf ("\tMontoImpInternosNotaCredito      = %.2f\n",	R.MontoImpInternosNotaCredito	);
				logger->Logf ("\tMontoPercepcionesNotaCredito     = %.2f\n",	R.MontoPercepcionesNotaCredito	);
				logger->Logf ("\tMontoIVANoInscriptoNotaCredito   = %.2f\n",	R.MontoIVANoInscriptoNotaCredito);
				logger->Logf ("\tUltimoRemito                     = %lu\n",		R.UltimoRemito					);
				logger->Logf ("\tCantidadNCCanceladas             = %u\n",		R.CantidadNCCanceladas			);
				logger->Logf ("\tCantidadDFBCEmitidos             = %u\n",		R.CantidadDFBCEmitidos			);
				logger->Logf ("\tCantidadDFAEmitidos              = %u\n",		R.CantidadDFAEmitidos			);
				logger->Logf ("\tCantidadNCBCEmitidas             = %u\n",		R.CantidadNCBCEmitidas			);
				logger->Logf ("\tCantidadNCAEmitidas              = %u\n",		R.CantidadNCAEmitidas			);

			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void ImprimirReporteZ(char *inicio, char *fin){
			logger->Log ("Imprimiendo Reporte Z entre fechas: Fecha Inicio: " + string(inicio) + " Fecha Fin: " + string(fin));

			ImpresorFiscal::FECHA FIni (inicio), FFin (fin);
			// FIni.anio = 1999;	FIni.mes  = 1;	FIni.dia  = 1;
			// FFin.anio = 2050;	FFin.mes  = 12;	FFin.dia  = 31;

			// Reporte Global por Rango de Fechas
			impresor->ReporteZPorFechas (FIni, FFin, true);
			// Reporte Individual por Rango de Fechas
			impresor->ReporteZPorFechas (FIni, FFin, false);
		}

		void ImprimirReporteZ(int inicio, int fin){
			logger->Log ("Imprimiendo Reporte Z entre numeros: Numero Inicio: " + Logger::IntToStr(inicio) + " Numero Fin: " + Logger::IntToStr(fin));

			// Reporte Global por Rango de Zetas
			impresor->ReporteZPorNumeros (inicio, fin, true);
			// Reporte Individual por Rango de Zetas
			impresor->ReporteZPorNumeros (inicio, fin, false);
		}
	private:
		Logger *logger;
		ImpresorFiscal *impresor;

		void EstablecerManejadorDeEventos(){
			EventosHasar715 *E = new EventosHasar715(impresor, logger);
			impresor->EstablecerManejadorDeEventos (E);
		}

		void ConfigurarImpresor(char *IniPath){
			CIniReader *iniReader = new CIniReader(IniPath);
			int puerto = iniReader->ReadInteger("CONFIG", "PUERTO", 1);
			logger->Log("PUERTO: " + Logger::IntToStr(puerto));
				
			// Estableciendo tipo de Transporte
			impresor->EstablecerPuertoSerie (puerto, 9600);
		}
	};
}
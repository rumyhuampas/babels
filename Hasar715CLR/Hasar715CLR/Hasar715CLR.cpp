#pragma once

#include "P715_403.h"

#include "IniReader.h"
#include "Logger.h"
#include "Hasar715CLRConfig.h"
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
				ConfigurarImpresor(IniPath);
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}


		//***********************************REPORTE Z ******************************************

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

		void ImprimirReporteZ(char *fechaInicio, char *fechaFin, bool global){
			try{
				logger->Log ("Imprimiendo Reporte Z entre fechas: Fecha Inicio: " + string(fechaInicio) + " Fecha Fin: " + string(fechaFin));

				ImpresorFiscal::FECHA FIni (fechaInicio), FFin (fechaFin);
				// FIni.anio = 1999;	FIni.mes  = 1;	FIni.dia  = 1;	---> 990101
				// FFin.anio = 2050;	FFin.mes  = 12;	FFin.dia  = 31;	---> 501231

				// Reporte Global/Individual por Rango de Fechas
				impresor->ReporteZPorFechas (FIni, FFin, global);
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void ImprimirReporteZ(int numeroInicio, int numeroFin, bool global){
			try{
				logger->Log ("Imprimiendo Reporte Z entre numeros: Numero Inicio: " + Logger::IntToStr(numeroInicio) + " Numero Fin: " + Logger::IntToStr(numeroFin));

				// Reporte Global/Individual por Rango de Zetas
				impresor->ReporteZPorNumeros (numeroInicio, numeroFin, global);
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void ImprimirReporteZ(char *fecha){
			ImpresorFiscal::FECHA FechaZ;
			unsigned NumeroZ;

			ImpresorFiscal::RTA_ReporteZIndividual R;

			// Reporte Z Individual por Fecha
			FechaZ = ImpresorFiscal::FECHA (fecha);	// ("050928");	// ("050727");
			impresor->ReporteZIndividualPorFecha (FechaZ, &R);

			logger->Log ("Reporte Z Individual por Fecha");
			logger->Logf ("Fecha: %02u/%02u/%04u - Zeta Nº: %04u",
					 R.FechaZeta.dia (), R.FechaZeta.mes (), R.FechaZeta.anio (), R.NumeroZeta		);
			logger->Logf ("\tUltimoDocFiscalBC                = %lu",	R.UltimoDocFiscalBC				);
			logger->Logf ("\tUltimoDocFiscalA                 = %lu",	R.UltimoDocFiscalA				);
			logger->Logf ("\tMontoVentasDocFiscal             = %.2f",  R.MontoVentasDocFiscal			);
			logger->Logf ("\tMontoIVADocFiscal                = %.2f",  R.MontoIVADocFiscal				);
			logger->Logf ("\tMontoImpInternosDocFiscal        = %.2f",  R.MontoImpInternosDocFiscal		);
			logger->Logf ("\tMontoPercepcionesDocFiscal       = %.2f",  R.MontoPercepcionesDocFiscal	);
			logger->Logf ("\tMontoIVANoInscriptoDocFiscal     = %.2f",  R.MontoIVANoInscriptoDocFiscal  );
			logger->Logf ("\tUltimaNotaCreditoBC              = %lu",	R.UltimaNotaCreditoBC			);
			logger->Logf ("\tUltimaNotaCreditoA               = %lu",	R.UltimaNotaCreditoA			);
			logger->Logf ("\tMontoVentasNotaCredito           = %.2f",  R.MontoVentasNotaCredito		);
			logger->Logf ("\tMontoIVANotaCredito              = %.2f",  R.MontoIVANotaCredito			);
			logger->Logf ("\tMontoImpInternosNotaCredito      = %.2f",  R.MontoImpInternosNotaCredito	);
			logger->Logf ("\tMontoPercepcionesNotaCredito     = %.2f",  R.MontoPercepcionesNotaCredito  );
			logger->Logf ("\tMontoIVANoInscriptoNotaCredito   = %.2f",  R.MontoIVANoInscriptoNotaCredito);
			logger->Logf ("\tUltimoRemito                     = %lu",	R.UltimoRemito					);
		}

		void ImprimirReporteZ(int numero){
			// Reporte Z Individual por Número
			ImpresorFiscal::RTA_ReporteZIndividual R;
			//NumeroZ = 1;
			impresor->ReporteZIndividualPorNumero (numero, &R);

			logger->Log ("Reporte Z Individual por Número");
			logger->Logf ("Zeta Nº: %04u - Fecha: %02u/%02u/%04u",
					 R.NumeroZeta, R.FechaZeta.dia (), R.FechaZeta.mes (), R.FechaZeta.anio ()		);
			logger->Logf ("\tUltimoDocFiscalBC                = %lu",	R.UltimoDocFiscalBC				);
			logger->Logf ("\tUltimoDocFiscalA                 = %lu",	R.UltimoDocFiscalA				);
			logger->Logf ("\tMontoVentasDocFiscal             = %.2f",  R.MontoVentasDocFiscal			);
			logger->Logf ("\tMontoIVADocFiscal                = %.2f",  R.MontoIVADocFiscal				);
			logger->Logf ("\tMontoImpInternosDocFiscal        = %.2f",  R.MontoImpInternosDocFiscal		);
			logger->Logf ("\tMontoPercepcionesDocFiscal       = %.2f",  R.MontoPercepcionesDocFiscal	);
			logger->Logf ("\tMontoIVANoInscriptoDocFiscal     = %.2f",  R.MontoIVANoInscriptoDocFiscal  );
			logger->Logf ("\tUltimaNotaCreditoBC              = %lu",	R.UltimaNotaCreditoBC			);
			logger->Logf ("\tUltimaNotaCreditoA               = %lu",	R.UltimaNotaCreditoA			);
			logger->Logf ("\tMontoVentasNotaCredito           = %.2f",  R.MontoVentasNotaCredito		);
			logger->Logf ("\tMontoIVANotaCredito              = %.2f",  R.MontoIVANotaCredito			);
			logger->Logf ("\tMontoImpInternosNotaCredito      = %.2f",  R.MontoImpInternosNotaCredito	);
			logger->Logf ("\tMontoPercepcionesNotaCredito     = %.2f",  R.MontoPercepcionesNotaCredito  );
			logger->Logf ("\tMontoIVANoInscriptoNotaCredito   = %.2f",  R.MontoIVANoInscriptoNotaCredito);
			logger->Logf ("\tUltimoRemito                     = %lu",	R.UltimoRemito					);
		}

		void ObtenerCapacidadZ(){
			ImpresorFiscal::RTA_CapacidadRestante R;
			
			impresor->CapacidadRestante (&R);
			logger->Logf ("Total Cierres Z:     %4u", R.TotalCierresZeta);
			logger->Logf ("Cierre Z actual:     %4u", R.CierreZetaActual);
			logger->Logf ("Cierres Z restantes: %4u", R.TotalCierresZeta - R.CierreZetaActual);
		}

		//***********************************************

		//************************** REPORTE X ************************
		void ImprimirReporteX(){
			ImpresorFiscal::RTA_ReporteZX R;
			
			impresor->ReporteX (&R);
					
			logger->Logf ("Reporte X Nº %u",							R.NumeroReporte					);
			logger->Logf ("\tCantidadDFCancelados             = %u",	R.CantidadDFCancelados			);
			logger->Logf ("\tCantidadDNFHEmitidos             = %u",	R.CantidadDNFHEmitidos			);
			logger->Logf ("\tCantidadDNFEmitidos              = %u",	R.CantidadDNFEmitidos			);
			logger->Logf ("\tCantidadDFEmitidos               = %u",	R.CantidadDFEmitidos			);
			logger->Logf ("\tUltimoDocFiscalBC                = %lu",	R.UltimoDocFiscalBC				);
			logger->Logf ("\tUltimoDocFiscalA                 = %lu",	R.UltimoDocFiscalA				);
			logger->Logf ("\tMontoVentasDocFiscal             = %.2f",	R.MontoVentasDocFiscal			);
			logger->Logf ("\tMontoIVADocFiscal                = %.2f",	R.MontoIVADocFiscal				);
			logger->Logf ("\tMontoImpInternosDocFiscal        = %.2f",	R.MontoImpInternosDocFiscal		);
			logger->Logf ("\tMontoPercepcionesDocFiscal       = %.2f",	R.MontoPercepcionesDocFiscal	);
			logger->Logf ("\tMontoIVANoInscriptoDocFiscal     = %.2f",	R.MontoIVANoInscriptoDocFiscal	);
			logger->Logf ("\tUltimaNotaCreditoBC              = %lu",	R.UltimaNotaCreditoBC			);
			logger->Logf ("\tUltimaNotaCreditoA               = %lu",	R.UltimaNotaCreditoA			);
			logger->Logf ("\tMontoVentasNotaCredito           = %.2f",	R.MontoVentasNotaCredito		);
			logger->Logf ("\tMontoIVANotaCredito              = %.2f",	R.MontoIVANotaCredito			);
			logger->Logf ("\tMontoImpInternosNotaCredito      = %.2f",	R.MontoImpInternosNotaCredito	);
			logger->Logf ("\tMontoPercepcionesNotaCredito     = %.2f",	R.MontoPercepcionesNotaCredito	);
			logger->Logf ("\tMontoIVANoInscriptoNotaCredito   = %.2f",	R.MontoIVANoInscriptoNotaCredito);
			logger->Logf ("\tUltimoRemito                     = %lu",	R.UltimoRemito					);
			logger->Logf ("\tCantidadNCCanceladas             = %u",	R.CantidadNCCanceladas			);
			logger->Logf ("\tCantidadDFBCEmitidos             = %u",	R.CantidadDFBCEmitidos			);
			logger->Logf ("\tCantidadDFAEmitidos              = %u",	R.CantidadDFAEmitidos			);
			logger->Logf ("\tCantidadNCBCEmitidas             = %u",	R.CantidadNCBCEmitidas			);
			logger->Logf ("\tCantidadNCAEmitidas              = %u",	R.CantidadNCAEmitidas			);
		}

		//********************* DNF **********************

		void AbrirDNF(){
			try{
				logger->Log ("Abriendo DNF Estacion Ticket");
				impresor->AbrirDNF(ImpresorFiscal::ESTACION_TICKET);
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void CerrarDNF(){
			try{
				logger -> Log("Cerrando DNF");
				impresor->CerrarDNF();
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void ImprimirTextoNoFiscal(char *text){
			try{
				logger->Log ("Imprimiendo Texto No Fiscal");
				impresor->ImprimirTextoNoFiscal (string(text));
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void ImprimirCodigoDeBarras(char *codigo, bool imprimirNumeros, bool imprimirAhora){
			try{
				logger->Log ("Imprimiendo Codigo de barras");
				impresor->ImprimirCodigoDeBarras(ImpresorFiscal::CODIGO_TIPO_EAN_13, string(codigo), imprimirNumeros, imprimirAhora);
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		//**********************************************

		//***********************REPORTE ESTADO**********************

		void ObtenerEstadoInterno(){
			try{
				hasarConfig->ObtenerEstadoInterno();
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void ObtenerDatosDeInicializacion(){
			try{
				hasarConfig->ObtenerDatosDeInicializacion();
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void ObtenerDatosMemoriaDeTrabajo(){
			try{
				hasarConfig->ObtenerDatosMemoriaDeTrabajo();
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void CambiarResponsabilidadIVA(){
			try{
				hasarConfig->CambiarResponsabilidadIVA();
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void CambiarCodigoIngresosBrutos(char *codigo){
			try{
				hasarConfig->CambiarCodigoIngresosBrutos(codigo);
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void CambiarFechaInicioActividades(char *fechaInicio){
			try{
				hasarConfig->CambiarFechaInicioActividades(fechaInicio);
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void ObtenerReporteStatusImpresor(){
			try{
				hasarConfig->ObtenerReporteStatusImpresor();
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void ObtenerReporteStatusFiscal(){
			try{
				hasarConfig->ObtenerReporteStatusFiscal();
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void ObtenerUltimosDocumentos(){
			try{
				hasarConfig->ObtenerUltimosDocumentos();
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void EstablecerFechaHora(char *fecha, char *hora){
			try{
				hasarConfig->EstablecerFechaHora(fecha, hora);
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void ObtenerConfiguracionCF(){
			try{
				hasarConfig->ObtenerConfiguracionCF();
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}

		void ConfigurarControlador(char *parametro, char *valor){
			try{
				hasarConfig->ConfigurarControlador(parametro, valor);
			}
			catch(Excepcion &e)
			{
				logger -> Log(e);
			}
		}
	private:
		Logger *logger;
		ImpresorFiscal *impresor;
		Hasar715CLRConfig *hasarConfig;

		void ConfigurarImpresor(char *IniPath){
			logger->Log("Configurando Impresor. " + string(IniPath));

			hasarConfig = new Hasar715CLRConfig(impresor, logger);
			hasarConfig->EstablecerManejadorDeEventos();

			//Leyendo Ini
			CIniReader *iniReader = new CIniReader(IniPath);

			int puerto = iniReader->ReadInteger("CONFIG", "PUERTO", 1);
			int interlineado = iniReader->ReadInteger("CONFIG", "INTERLINEADO", 6);
			logger->Log("PUERTO: " + Logger::IntToStr(puerto));
			//logger->Log("INTERLINEADO: " + Logger::IntToStr(interlineado));
			
			hasarConfig->EstablecerPuertoSerie(puerto);
			hasarConfig->EstablecerInterlineadoDeImpresion(interlineado);
		}
	};
}
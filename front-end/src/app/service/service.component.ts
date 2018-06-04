import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {map, catchError, retry} from 'rxjs/operators';
import { getHeader } from '../utils/header.component';
import { environment } from '../../environments/environment';

@Injectable()
export class Service {

constructor(private http: HttpClient) {}
    /**
     * Obtem os dados a partir da rota passada
     * @param route rota a ser consumida
     * @param value valor do parametro de busca
     */
    Get(route: string, value?: any): Observable<any> {
        return this.http.get(`${environment.apiEndPoint}${route}${value !== undefined? '/'+ value: ''}`, getHeader())
        .pipe(
            retry(3),
            map(response => response),
            catchError(this.handleError));
    }

    /**
     * Envia um objeto para persistencia.
     * @param route rota a ser consumida
     * @param obj objeto a ser persistido
     */
    Post(route: string, obj: any) {
        return this.http.post<any>(`${environment.apiEndPoint}${route}/cadastrar`, obj, getHeader())
        .pipe(catchError(this.handleError));
    }

    /**
     * Envia um objeto para atualização.
     * @param route rota a ser consumida
     * @param obj objeto a ser atualizado
     */    
    Put(route: string, obj: any) {
        return this.http.put(`${environment.apiEndPoint}${route}`, obj, getHeader()).pipe(catchError(this.handleError));
    }

    /**
     * Deleta um objeto a partir do valor passado na requisição.
     * @param route rota a ser consumida
     * @param value valor do parametro da rota
     */
    Delete(route: string, value: any) {
        return this.http.delete(`${environment.apiEndPoint}/${route}/${value}`, getHeader()).pipe(catchError(this.handleError));
    }


    private handleError(error: HttpErrorResponse) {
        if (error.error instanceof ErrorEvent) {
          // A client-side or network error occurred. Handle it accordingly.
          for (let index = 0; index < error['errors'].length; index++) {
            console.error('Um erro aconteceu :', error.error instanceof Array? error.error['errors'] : error.error);
          }
        } else {
          // The backend returned an unsuccessful response code.
          // The response body may contain clues as to what went wrong,
          console.error(
            `Backend retornou o código ${error.status}, ` +
            `O erro é: ${error.error instanceof Array? error.error['errors'] : error.error.message}`);
        }
        console.log(error.error.message);
        // return an observable with a user-facing error message
        return throwError(
          'Algo ruim aconteceu; por favor tente novamente mais tarde.');
      };
}
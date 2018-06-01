import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
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
        return this.http.get(`${environment.apiEndPoint}/${route}/${value}`, getHeader()).pipe(map(response => response));
    }

    /**
     * Envia um objeto para persistencia.
     * @param route rota a ser consumida
     * @param obj objeto a ser persistido
     */
    Post(route: string, obj: any) {
        return this.http.post(`${environment.apiEndPoint}/${route}`, obj, getHeader()).pipe(map(response => response));
    }

    /**
     * Envia um objeto para atualização.
     * @param route rota a ser consumida
     * @param obj objeto a ser atualizado
     */    
    Put(route: string, obj: any) {
        return this.http.post(`${environment.apiEndPoint}/${route}`, obj, getHeader()).pipe(map(response => response));
    }
    /**
     * Deleta um objeto a partir do valor do value passado.
     * @param route rota a ser consumida
     * @param value valor do parametro da rota
     */
    Delete(route: string, value: any) {
        return this.http.post(`${environment.apiEndPoint}/${route}/${value}`, getHeader()).pipe(map(response => response));
    }
}
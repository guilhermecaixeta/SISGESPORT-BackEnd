import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

/**
 * Interface padrão para os observables do sistema.
 */
export interface IObservableUtil {
    subject: Subject<any>;
    /**
     * Armazena os valores passados pelo sistema.
     */
    setValue(variable: any);
    /**
     * Retorna os valores passados pelo sistema.
     */
    getValue: any;
}
/**
 * Observable padrão do sistema.
 */
@Injectable()
export class ObservablePadrao implements IObservableUtil {
    subject = new BehaviorSubject<any>("");

    setValue(variable: any) {
        this.subject.next({ data: variable });
    }

    getValue = this.subject.asObservable();
}
import { Injectable } from "@angular/core";

@Injectable()
export class MaskField {
    public static dddMask: any[] = [/\d/, /\d/];
    public static cepMask: any[] = [/\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/];
    public static telefoneMask: any[] = [/\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/, /\d/];
    
}
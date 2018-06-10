import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServidorComponent } from './servidor.component';

const routes: Routes = [
    {
        path: '', component: ServidorComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ServidorRoutingModule {
}

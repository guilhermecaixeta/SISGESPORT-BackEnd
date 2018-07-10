import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CadastroCrudEtapa1Component } from './cadastro-crud-etapa1.component';

const routes: Routes = [
    {
        path: '', component: CadastroCrudEtapa1Component
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CadastroCrudEtapa1RoutingModule {
}

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProductRecord } from 'app/shared/model/product-record.model';
import { ProductRecordService } from './product-record.service';
import { ProductRecordComponent } from './product-record.component';
import { ProductRecordDetailComponent } from './product-record-detail.component';
import { ProductRecordUpdateComponent } from './product-record-update.component';
import { ProductRecordDeletePopupComponent } from './product-record-delete-dialog.component';
import { IProductRecord } from 'app/shared/model/product-record.model';

@Injectable({ providedIn: 'root' })
export class ProductRecordResolve implements Resolve<IProductRecord> {
    constructor(private service: ProductRecordService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ProductRecord> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ProductRecord>) => response.ok),
                map((productRecord: HttpResponse<ProductRecord>) => productRecord.body)
            );
        }
        return of(new ProductRecord());
    }
}

export const productRecordRoute: Routes = [
    {
        path: 'product-record',
        component: ProductRecordComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.productRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-record/:id/view',
        component: ProductRecordDetailComponent,
        resolve: {
            productRecord: ProductRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.productRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-record/new',
        component: ProductRecordUpdateComponent,
        resolve: {
            productRecord: ProductRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.productRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'product-record/:id/edit',
        component: ProductRecordUpdateComponent,
        resolve: {
            productRecord: ProductRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.productRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const productRecordPopupRoute: Routes = [
    {
        path: 'product-record/:id/delete',
        component: ProductRecordDeletePopupComponent,
        resolve: {
            productRecord: ProductRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.productRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

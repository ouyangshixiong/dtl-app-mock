import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StoreAuthRecord } from 'app/shared/model/store-auth-record.model';
import { StoreAuthRecordService } from './store-auth-record.service';
import { StoreAuthRecordComponent } from './store-auth-record.component';
import { StoreAuthRecordDetailComponent } from './store-auth-record-detail.component';
import { StoreAuthRecordUpdateComponent } from './store-auth-record-update.component';
import { StoreAuthRecordDeletePopupComponent } from './store-auth-record-delete-dialog.component';
import { IStoreAuthRecord } from 'app/shared/model/store-auth-record.model';

@Injectable({ providedIn: 'root' })
export class StoreAuthRecordResolve implements Resolve<IStoreAuthRecord> {
    constructor(private service: StoreAuthRecordService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<StoreAuthRecord> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<StoreAuthRecord>) => response.ok),
                map((storeAuthRecord: HttpResponse<StoreAuthRecord>) => storeAuthRecord.body)
            );
        }
        return of(new StoreAuthRecord());
    }
}

export const storeAuthRecordRoute: Routes = [
    {
        path: 'store-auth-record',
        component: StoreAuthRecordComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.storeAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'store-auth-record/:id/view',
        component: StoreAuthRecordDetailComponent,
        resolve: {
            storeAuthRecord: StoreAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.storeAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'store-auth-record/new',
        component: StoreAuthRecordUpdateComponent,
        resolve: {
            storeAuthRecord: StoreAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.storeAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'store-auth-record/:id/edit',
        component: StoreAuthRecordUpdateComponent,
        resolve: {
            storeAuthRecord: StoreAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.storeAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const storeAuthRecordPopupRoute: Routes = [
    {
        path: 'store-auth-record/:id/delete',
        component: StoreAuthRecordDeletePopupComponent,
        resolve: {
            storeAuthRecord: StoreAuthRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'dtlAppMockApp.storeAuthRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    StoreUserComponent,
    StoreUserDetailComponent,
    StoreUserUpdateComponent,
    StoreUserDeletePopupComponent,
    StoreUserDeleteDialogComponent,
    storeUserRoute,
    storeUserPopupRoute
} from './';

const ENTITY_STATES = [...storeUserRoute, ...storeUserPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StoreUserComponent,
        StoreUserDetailComponent,
        StoreUserUpdateComponent,
        StoreUserDeleteDialogComponent,
        StoreUserDeletePopupComponent
    ],
    entryComponents: [StoreUserComponent, StoreUserUpdateComponent, StoreUserDeleteDialogComponent, StoreUserDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockStoreUserModule {}

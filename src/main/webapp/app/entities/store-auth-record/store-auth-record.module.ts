import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    StoreAuthRecordComponent,
    StoreAuthRecordDetailComponent,
    StoreAuthRecordUpdateComponent,
    StoreAuthRecordDeletePopupComponent,
    StoreAuthRecordDeleteDialogComponent,
    storeAuthRecordRoute,
    storeAuthRecordPopupRoute
} from './';

const ENTITY_STATES = [...storeAuthRecordRoute, ...storeAuthRecordPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StoreAuthRecordComponent,
        StoreAuthRecordDetailComponent,
        StoreAuthRecordUpdateComponent,
        StoreAuthRecordDeleteDialogComponent,
        StoreAuthRecordDeletePopupComponent
    ],
    entryComponents: [
        StoreAuthRecordComponent,
        StoreAuthRecordUpdateComponent,
        StoreAuthRecordDeleteDialogComponent,
        StoreAuthRecordDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockStoreAuthRecordModule {}

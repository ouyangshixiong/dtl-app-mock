import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    ProductRecordComponent,
    ProductRecordDetailComponent,
    ProductRecordUpdateComponent,
    ProductRecordDeletePopupComponent,
    ProductRecordDeleteDialogComponent,
    productRecordRoute,
    productRecordPopupRoute
} from './';

const ENTITY_STATES = [...productRecordRoute, ...productRecordPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProductRecordComponent,
        ProductRecordDetailComponent,
        ProductRecordUpdateComponent,
        ProductRecordDeleteDialogComponent,
        ProductRecordDeletePopupComponent
    ],
    entryComponents: [
        ProductRecordComponent,
        ProductRecordUpdateComponent,
        ProductRecordDeleteDialogComponent,
        ProductRecordDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockProductRecordModule {}

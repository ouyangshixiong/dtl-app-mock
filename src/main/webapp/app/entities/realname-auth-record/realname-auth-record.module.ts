import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    RealnameAuthRecordComponent,
    RealnameAuthRecordDetailComponent,
    RealnameAuthRecordUpdateComponent,
    RealnameAuthRecordDeletePopupComponent,
    RealnameAuthRecordDeleteDialogComponent,
    realnameAuthRecordRoute,
    realnameAuthRecordPopupRoute
} from './';

const ENTITY_STATES = [...realnameAuthRecordRoute, ...realnameAuthRecordPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RealnameAuthRecordComponent,
        RealnameAuthRecordDetailComponent,
        RealnameAuthRecordUpdateComponent,
        RealnameAuthRecordDeleteDialogComponent,
        RealnameAuthRecordDeletePopupComponent
    ],
    entryComponents: [
        RealnameAuthRecordComponent,
        RealnameAuthRecordUpdateComponent,
        RealnameAuthRecordDeleteDialogComponent,
        RealnameAuthRecordDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockRealnameAuthRecordModule {}

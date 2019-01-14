import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    BankcardAuthRecordComponent,
    BankcardAuthRecordDetailComponent,
    BankcardAuthRecordUpdateComponent,
    BankcardAuthRecordDeletePopupComponent,
    BankcardAuthRecordDeleteDialogComponent,
    bankcardAuthRecordRoute,
    bankcardAuthRecordPopupRoute
} from './';

const ENTITY_STATES = [...bankcardAuthRecordRoute, ...bankcardAuthRecordPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BankcardAuthRecordComponent,
        BankcardAuthRecordDetailComponent,
        BankcardAuthRecordUpdateComponent,
        BankcardAuthRecordDeleteDialogComponent,
        BankcardAuthRecordDeletePopupComponent
    ],
    entryComponents: [
        BankcardAuthRecordComponent,
        BankcardAuthRecordUpdateComponent,
        BankcardAuthRecordDeleteDialogComponent,
        BankcardAuthRecordDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockBankcardAuthRecordModule {}

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    SiteAuthRecordComponent,
    SiteAuthRecordDetailComponent,
    SiteAuthRecordUpdateComponent,
    SiteAuthRecordDeletePopupComponent,
    SiteAuthRecordDeleteDialogComponent,
    siteAuthRecordRoute,
    siteAuthRecordPopupRoute
} from './';

const ENTITY_STATES = [...siteAuthRecordRoute, ...siteAuthRecordPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SiteAuthRecordComponent,
        SiteAuthRecordDetailComponent,
        SiteAuthRecordUpdateComponent,
        SiteAuthRecordDeleteDialogComponent,
        SiteAuthRecordDeletePopupComponent
    ],
    entryComponents: [
        SiteAuthRecordComponent,
        SiteAuthRecordUpdateComponent,
        SiteAuthRecordDeleteDialogComponent,
        SiteAuthRecordDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockSiteAuthRecordModule {}

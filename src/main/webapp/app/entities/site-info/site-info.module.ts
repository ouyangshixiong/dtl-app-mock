import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    SiteInfoComponent,
    SiteInfoDetailComponent,
    SiteInfoUpdateComponent,
    SiteInfoDeletePopupComponent,
    SiteInfoDeleteDialogComponent,
    siteInfoRoute,
    siteInfoPopupRoute
} from './';

const ENTITY_STATES = [...siteInfoRoute, ...siteInfoPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SiteInfoComponent,
        SiteInfoDetailComponent,
        SiteInfoUpdateComponent,
        SiteInfoDeleteDialogComponent,
        SiteInfoDeletePopupComponent
    ],
    entryComponents: [SiteInfoComponent, SiteInfoUpdateComponent, SiteInfoDeleteDialogComponent, SiteInfoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockSiteInfoModule {}

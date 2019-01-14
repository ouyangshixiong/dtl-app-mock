import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { DtlAppMockIspModule } from './isp/isp.module';
import { DtlAppMockBankcardInfoModule } from './bankcard-info/bankcard-info.module';
import { DtlAppMockEnterpriseInfoModule } from './enterprise-info/enterprise-info.module';
import { DtlAppMockEnterpriseAuthRecordModule } from './enterprise-auth-record/enterprise-auth-record.module';
import { DtlAppMockRealnameInfoModule } from './realname-info/realname-info.module';
import { DtlAppMockDTLUserModule } from './dtl-user/dtl-user.module';
import { DtlAppMockUserLevelModule } from './user-level/user-level.module';
import { DtlAppMockUserRoleModule } from './user-role/user-role.module';
import { DtlAppMockRecommendRecordModule } from './recommend-record/recommend-record.module';
import { DtlAppMockSiteAuthRecordModule } from './site-auth-record/site-auth-record.module';
import { DtlAppMockImageModule } from './image/image.module';
import { DtlAppMockStoreModule } from './store/store.module';
import { DtlAppMockStaffModule } from './staff/staff.module';
import { DtlAppMockSiteInfoModule } from './site-info/site-info.module';
import { DtlAppMockRealnameAuthRecordModule } from './realname-auth-record/realname-auth-record.module';
import { DtlAppMockBankcardAuthRecordModule } from './bankcard-auth-record/bankcard-auth-record.module';
import { DtlAppMockProductModule } from './product/product.module';
import { DtlAppMockStoreUserModule } from './store-user/store-user.module';
import { DtlAppMockStoreAuthRecordModule } from './store-auth-record/store-auth-record.module';
import { DtlAppMockProductRecordModule } from './product-record/product-record.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        DtlAppMockIspModule,
        DtlAppMockBankcardInfoModule,
        DtlAppMockEnterpriseInfoModule,
        DtlAppMockEnterpriseAuthRecordModule,
        DtlAppMockRealnameInfoModule,
        DtlAppMockDTLUserModule,
        DtlAppMockUserLevelModule,
        DtlAppMockUserRoleModule,
        DtlAppMockRecommendRecordModule,
        DtlAppMockSiteAuthRecordModule,
        DtlAppMockImageModule,
        DtlAppMockStoreModule,
        DtlAppMockStaffModule,
        DtlAppMockSiteInfoModule,
        DtlAppMockRealnameAuthRecordModule,
        DtlAppMockBankcardAuthRecordModule,
        DtlAppMockProductModule,
        DtlAppMockStoreUserModule,
        DtlAppMockStoreAuthRecordModule,
        DtlAppMockProductRecordModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockEntityModule {}

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { EnterpriseInfoDetailComponent } from 'app/entities/enterprise-info/enterprise-info-detail.component';
import { EnterpriseInfo } from 'app/shared/model/enterprise-info.model';

describe('Component Tests', () => {
    describe('EnterpriseInfo Management Detail Component', () => {
        let comp: EnterpriseInfoDetailComponent;
        let fixture: ComponentFixture<EnterpriseInfoDetailComponent>;
        const route = ({ data: of({ enterpriseInfo: new EnterpriseInfo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [EnterpriseInfoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EnterpriseInfoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EnterpriseInfoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.enterpriseInfo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

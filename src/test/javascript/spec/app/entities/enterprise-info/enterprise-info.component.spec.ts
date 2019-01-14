/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { EnterpriseInfoComponent } from 'app/entities/enterprise-info/enterprise-info.component';
import { EnterpriseInfoService } from 'app/entities/enterprise-info/enterprise-info.service';
import { EnterpriseInfo } from 'app/shared/model/enterprise-info.model';

describe('Component Tests', () => {
    describe('EnterpriseInfo Management Component', () => {
        let comp: EnterpriseInfoComponent;
        let fixture: ComponentFixture<EnterpriseInfoComponent>;
        let service: EnterpriseInfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [EnterpriseInfoComponent],
                providers: []
            })
                .overrideTemplate(EnterpriseInfoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnterpriseInfoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnterpriseInfoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EnterpriseInfo(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.enterpriseInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
